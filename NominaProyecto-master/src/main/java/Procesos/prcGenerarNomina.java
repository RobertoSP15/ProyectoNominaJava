package Procesos;

import ManejoDeArchivos.ManejoDeArchivos;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class prcGenerarNomina extends JFrame {

    private final ManejoDeArchivos manejadorEmpleados;
    private final ManejoDeArchivos manejadorCooperativa;
    private final ManejoDeArchivos manejadorNomina;

    private final JComboBox<Integer> cmbMes;
    private final JComboBox<Integer> cmbAnio;
    private final JButton btnGenerar;

    private final JTable tablaResumen;

    private static final double TASA_ARS = 0.0304; // 3.04%
    private static final double TASA_AFP = 0.0287; // 2.87%

    public prcGenerarNomina() {
        this.manejadorEmpleados = new ManejoDeArchivos("empleados.txt");
        this.manejadorCooperativa = new ManejoDeArchivos("cooperativa.txt");
        this.manejadorNomina = new ManejoDeArchivos("nomina.txt");

        setTitle("Generar Nómina");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Mes"), gbc);
        gbc.gridx = 1;
        cmbMes = new JComboBox<>();
        for (int m = 1; m <= 12; m++) cmbMes.addItem(m);
        form.add(cmbMes, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Año"), gbc);
        gbc.gridx = 1;
        cmbAnio = new JComboBox<>();
        int currentYear = java.time.LocalDate.now().getYear();
        for (int a = currentYear - 5; a <= currentYear + 5; a++) cmbAnio.addItem(a);
        cmbAnio.setSelectedItem(currentYear);
        form.add(cmbAnio, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        btnGenerar = new JButton("Generar Nómina");
        form.add(btnGenerar, gbc);

        String[] columnas = new String[]{
            "ID Empleado", "Bruto", "AFP", "ARS", "Coop", "ISR", "Neto"
        };
        tablaResumen = new JTable(new DefaultTableModel(columnas, 0));

        setLayout(new BorderLayout());
        add(form, BorderLayout.NORTH);
        add(new JScrollPane(tablaResumen), BorderLayout.CENTER);

        btnGenerar.addActionListener(e -> generar());

        setSize(800, 500);
    }

    private void generar() {
        int mes = (int) cmbMes.getSelectedItem();
        int anio = (int) cmbAnio.getSelectedItem();

        String fechaNomina = String.format("%04d-%02d-30", anio, mes);

        try {
            List<String> empleados = manejadorEmpleados.leerTodasLasLineas();
            List<String> cooperativa = manejadorCooperativa.leerTodasLasLineas();
            List<String> nominaActual = manejadorNomina.leerTodasLasLineas();

            int nextIdNomina = calcularSiguienteId(nominaActual);

            DefaultTableModel model = (DefaultTableModel) tablaResumen.getModel();
            model.setRowCount(0);

            List<String> nuevasLineasNomina = new ArrayList<>();

            for (String empLinea : empleados) {
                if (empLinea == null || empLinea.trim().isEmpty()) continue;
                String[] p = empLinea.split(",");
                if (p.length < 12) continue;

                String idEmp = p[0].trim();
                boolean perteneceCoop = Boolean.parseBoolean(p[10].trim());
                double salarioBruto = parseDoubleSafe(p[11].trim());

                double valorARS = round2(salarioBruto * TASA_ARS);
                double valorAFP = round2(salarioBruto * TASA_AFP);

                double baseISR = salarioBruto - valorARS - valorAFP;
                if (baseISR < 0) baseISR = 0;
                double valorISR = round2(calcularISR(baseISR));

                double valorCoop = 0.0;
                if (perteneceCoop) {
                    CoopInfo info = buscarCoopInfo(cooperativa, idEmp);
                    if (info != null) {
                        valorCoop = round2(salarioBruto * (info.porcentaje / 100.0));
                        // Actualizar balance en memoria; se escribirá luego
                        info.balance = round2(info.balance + valorCoop);
                        reemplazarLineaCoop(cooperativa, info);
                    }
                }

                double sueldoNeto = round2(salarioBruto - (valorAFP + valorARS + valorISR + valorCoop));

                String lineaNomina = String.join(",",
                        String.valueOf(nextIdNomina++), // Id_Nomina
                        idEmp, // Id_Empleado
                        fechaNomina, // Fecha_Nomina
                        format2(salarioBruto), // Salario_Bruto
                        format2(valorAFP), // Valor_AFP
                        format2(valorARS), // Valor_ARS
                        format2(valorCoop), // Valor_Coop
                        format2(valorISR), // Valor_ISR
                        format2(sueldoNeto), // Sueldo_Neto
                        String.valueOf(true) // Status_Nomina
                );
                nuevasLineasNomina.add(lineaNomina);

                model.addRow(new Object[]{idEmp, format2(salarioBruto), format2(valorAFP), format2(valorARS), format2(valorCoop), format2(valorISR), format2(sueldoNeto)});
            }

            // Persistir: primero cooperativa (balances), luego nómina acumulada
            escribirArchivoCompleto(manejadorCooperativa, cooperativa);
            for (String ln : nuevasLineasNomina) {
                manejadorNomina.agregarLinea(ln);
            }

            JOptionPane.showMessageDialog(this, "Nómina generada y guardada para " + fechaNomina + ".");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error procesando archivos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int calcularSiguienteId(List<String> lineasNomina) {
        int max = 0;
        for (String ln : lineasNomina) {
            if (ln == null || ln.trim().isEmpty()) continue;
            String[] p = ln.split(",");
            if (p.length == 0) continue;
            try {
                max = Math.max(max, Integer.parseInt(p[0].trim()));
            } catch (NumberFormatException ignored) {}
        }
        return max + 1;
    }

    private static double parseDoubleSafe(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0.0; }
    }

    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    private static String format2(double v) {
        return String.format(java.util.Locale.US, "%.2f", v);
    }

    // ISR mensual según tabla provista en el enunciado
    private static double calcularISR(double base) {
        // Rangos mensuales RD$: 0 - 34,685 => 0%
        // 34,685.01 - 52,027.42 => 15% del excedente de 34,685.00
        // 52,027.43 - 72,260.25 => 2,603.13 + 20% del excedente de 52,027.42
        // >= 72,260.26 => 6,648.00 + 25% del excedente de 72,260.25
        final double R1 = 34685.00;
        final double R2 = 52027.42;
        final double R3 = 72260.25;

        if (base <= R1) return 0.0;
        if (base <= R2) return (base - R1) * 0.15;
        if (base <= R3) return 2603.13 + (base - R2) * 0.20;
        return 6648.00 + (base - R3) * 0.25;
    }

    private static class CoopInfo {
        String idEmpleado;
        double porcentaje;
        double balance;
        int index;
    }

    private static CoopInfo buscarCoopInfo(List<String> lineas, String idEmpleado) {
        for (int i = 0; i < lineas.size(); i++) {
            String ln = lineas.get(i);
            if (ln == null || ln.trim().isEmpty()) continue;
            String[] p = ln.split(",");
            if (p.length < 3) continue;
            if (p[0].trim().equals(idEmpleado)) {
                CoopInfo ci = new CoopInfo();
                ci.idEmpleado = idEmpleado;
                try { ci.porcentaje = Double.parseDouble(p[1].trim()); } catch (Exception e) { ci.porcentaje = 0.0; }
                try { ci.balance = Double.parseDouble(p[2].trim()); } catch (Exception e) { ci.balance = 0.0; }
                ci.index = i;
                return ci;
            }
        }
        return null;
    }

    private static void reemplazarLineaCoop(List<String> lineas, CoopInfo info) {
        String nueva = info.idEmpleado + "," + format2(info.porcentaje) + "," + format2(info.balance);
        lineas.set(info.index, nueva);
    }

    private static void escribirArchivoCompleto(ManejoDeArchivos manejador, List<String> nuevasLineas) throws IOException {
        // La clase ManejoDeArchivos no expone escribirTodasLasLineas, por lo que aplicamos un truco:
        // borramos línea a línea y volvemos a escribir. Para simplificar, reescribimos usando un archivo temporal en memoria.
        // Aquí, como no hay API pública, usamos: limpiar archivo escribiendo 0 líneas y luego append.
        // Implementación: leer actual y eliminar por contenido no es eficiente; mejor usar Files.write, pero no está expuesto.
        // Así que haremos: si el archivo tiene N líneas, modificamos cada una con la nueva línea correspondiente o eliminamos.
        // Para evitar complejidad, escribimos agregando sobre un archivo "limpio" mediante java.nio directamente.
        java.nio.file.Path path = java.nio.file.Paths.get(System.getProperty("user.dir"), "Data", extraerNombre(manejador));
        java.nio.file.Files.write(path, nuevasLineas);
    }

    private static String extraerNombre(ManejoDeArchivos m) {
        // El nombre de archivo está en la ruta del manejador; no hay getter público, reconstruimos por inferencia
        // Este método solo se usa para reescritura interna en esta clase
        // Dado que el manejador siempre se construye con nombre simple, lo recuperamos del toString del Path
        // pero no tenemos acceso. Usamos convención: ya sabemos cuál es el archivo por la instancia que pasamos
        // Por lo tanto, devolvemos nombre según instancia (hack controlado en este contexto)
        if (m == null) return "";
        // No podemos distinguir por reflexión de forma segura; sin embargo, en nuestras llamadas pasamos explícitamente
        // manejadorCooperativa, por lo que siempre escribiremos "cooperativa.txt".
        return "cooperativa.txt";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new prcGenerarNomina().setVisible(true));
    }
}