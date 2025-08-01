package Mantenimiento;

import ManejoDeArchivos.ManejoDeArchivos;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class mnEmpleados extends javax.swing.JFrame {

    private final ManejoDeArchivos manejadorEmpleados;
    private final ManejoDeArchivos manejadorDeptos;
    private final ManejoDeArchivos manejadorPuestos;
    private final ManejoDeArchivos manejadorCooperativa;
    
    private String estadoFormulario = "BUSCANDO";
    
    public mnEmpleados() {
        this.manejadorEmpleados = new ManejoDeArchivos("empleados.txt");
        this.manejadorDeptos = new ManejoDeArchivos("departamentos.txt");
        this.manejadorPuestos = new ManejoDeArchivos("puestos.txt");
        this.manejadorCooperativa = new ManejoDeArchivos("cooperativa.txt");
        
        initComponents();
        configuracionInicial();
    }
    
     private void configuracionInicial() {
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de Empleados");
        cargarComboBoxes();
        actualizarEstadoFormulario("BUSCANDO");
    }
    
      private void cargarComboBoxes() {
        try {
            List<String> deptos = manejadorDeptos.leerTodasLasLineas();
            DefaultComboBoxModel<String> modeloDeptos = new DefaultComboBoxModel<>();
            modeloDeptos.addElement("Seleccione un Departamento...");
            for (String depto : deptos) {
                String[] partes = depto.split(",");
                if (partes.length >= 2) { 
                    modeloDeptos.addElement(partes[1].trim()); 
                }
            }
            cmbDepartamento.setModel(modeloDeptos);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar departamentos.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            List<String> puestos = manejadorPuestos.leerTodasLasLineas();
            DefaultComboBoxModel<String> modeloPuestos = new DefaultComboBoxModel<>();
            modeloPuestos.addElement("Seleccione un Puesto...");
            for (String puesto : puestos) {
                 String[] partes = puesto.split(",");
                 if (partes.length >= 2) { 
                    modeloPuestos.addElement(partes[1].trim());
                 }
            }
            cmbPuesto.setModel(modeloPuestos);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar puestos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     private void actualizarEstadoFormulario(String nuevoEstado) {
        this.estadoFormulario = nuevoEstado;
        jLabel2.setText("Estado: " + nuevoEstado);

        switch (nuevoEstado) {
            case "BUSCANDO":
                limpiarFormulario(true);
                habilitarCampos(false);
                tfID.setEnabled(true);
                btnVerificar.setEnabled(true);
                btnGuardar.setEnabled(false);
                break;
            case "CREANDO":
                habilitarCampos(true);
                tfID.setEnabled(false);
                btnVerificar.setEnabled(false);
                btnGuardar.setEnabled(true);
                dcFechaIngreso.setDate(new Date()); 
                dcFechaIngreso.setEnabled(false);
                break;
            case "MODIFICANDO":
                habilitarCampos(true);
                tfID.setEnabled(false);
                btnVerificar.setEnabled(false);
                btnGuardar.setEnabled(true);
                dcFechaIngreso.setEnabled(false); 
                break;
        }
    }
     
      private void habilitarCampos(boolean habilitar) {
        tfNombre.setEnabled(habilitar);
        tfApellidoP.setEnabled(habilitar);
        tfApellidoM.setEnabled(habilitar);
        tfDireccion.setEnabled(habilitar);
        rbMasculino.setEnabled(habilitar);
        rbFemenino.setEnabled(habilitar);
        tfTel.setEnabled(habilitar);
        cmbDepartamento.setEnabled(habilitar);
        cmbPuesto.setEnabled(habilitar);
        dcFechaIngreso.setEnabled(habilitar);
        tfSalario.setEnabled(habilitar);
        jCheckBox1.setEnabled(habilitar);
    }
      
        private void limpiarFormulario(boolean limpiarId) {
        if(limpiarId) tfID.setText("");
        tfNombre.setText("");
        tfApellidoP.setText("");
        tfApellidoM.setText("");
        tfDireccion.setText("");
        rbMasculino.setSelected(true);
        tfTel.setText("");
        cmbDepartamento.setSelectedIndex(0);
        cmbPuesto.setSelectedIndex(0);
        dcFechaIngreso.setDate(null);
        tfSalario.setText("");
        jCheckBox1.setSelected(false);
    }
        
         private void cargarDatosEmpleado(String[] datos) {
        tfNombre.setText(datos[1]);
        tfApellidoP.setText(datos[2]);
        tfApellidoM.setText(datos[3]);
        tfDireccion.setText(datos[4]);
        tfTel.setText(datos[5]);
        if(Boolean.parseBoolean(datos[6])) rbMasculino.setSelected(true); else rbFemenino.setSelected(true);
        try {
            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(datos[8]);
            dcFechaIngreso.setDate(fecha);
        } catch (Exception e) {
            dcFechaIngreso.setDate(null);
        }
        jCheckBox1.setSelected(Boolean.parseBoolean(datos[10]));
        tfSalario.setText(datos[11]);
    }
         
          private boolean validarCamposObligatorios(){
        if(tfID.getText().trim().isEmpty() ||
           tfNombre.getText().trim().isEmpty() ||
           tfApellidoP.getText().trim().isEmpty() ||
           tfApellidoM.getText().trim().isEmpty() ||
           tfDireccion.getText().trim().isEmpty() ||
           tfTel.getText().trim().isEmpty() ||
           cmbDepartamento.getSelectedIndex() == 0 ||
           cmbPuesto.getSelectedIndex() == 0 ||
           dcFechaIngreso.getDate() == null ||
           tfSalario.getText().trim().isEmpty()
           ){
            JOptionPane.showMessageDialog(this, "Todos los campos obligatorios deben ser llenados.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
          
           private boolean validarDatosExternos() throws IOException {
        return true; 
    }
           
            private boolean validarLogicaCooperativa() throws IOException{
        String idEmpleado = tfID.getText().trim();
        if(estadoFormulario.equals("MODIFICANDO") && !jCheckBox1.isSelected()){
             List<String> lineasCoop = manejadorCooperativa.leerTodasLasLineas();
             for(String linea : lineasCoop){
                 String[] partes = linea.split(",");
                 if(partes[0].trim().equals(idEmpleado)){
                     double balance = Double.parseDouble(partes[2].trim());
                     if(balance > 0){
                         JOptionPane.showMessageDialog(this, "No se puede quitar al empleado de la cooperativa porque tiene un balance acumulado mayor a cero.", "Error de Lógica", JOptionPane.ERROR_MESSAGE);
                         return false;
                     }
                 }
             }
        }
        return true;
    }

    private String construirLineaDeDatos() {
        String id = tfID.getText().trim();
        String nombre = tfNombre.getText().trim();
        String apellidoP = tfApellidoP.getText().trim();
        String apellidoM = tfApellidoM.getText().trim();
        String direccion = tfDireccion.getText().trim();
        String telefono = tfTel.getText().trim();
        String sexo = String.valueOf(rbMasculino.isSelected());
        // Falta obtener ID de ComboBox
        String idDepto = "0"; 
        String idPuesto = "0"; 
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaIngreso = formato.format(dcFechaIngreso.getDate());
        
        String coooperativa = String.valueOf(jCheckBox1.isSelected());
        String salario = tfSalario.getText().trim();

        return String.join(",", id, nombre, apellidoP, apellidoM, direccion, telefono, sexo, idDepto, fechaIngreso, idPuesto, coooperativa, salario);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lbID = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        btnVerificar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        lbApellidoP = new javax.swing.JLabel();
        lbApellidoM = new javax.swing.JLabel();
        lbDireccion = new javax.swing.JLabel();
        lbSexo = new javax.swing.JLabel();
        lbTel = new javax.swing.JLabel();
        lbDepto = new javax.swing.JLabel();
        lbPuesto = new javax.swing.JLabel();
        lbFecha = new javax.swing.JLabel();
        lbSalario = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        tfTel = new javax.swing.JTextField();
        tfApellidoM = new javax.swing.JTextField();
        tfApellidoP = new javax.swing.JTextField();
        tfDireccion = new javax.swing.JTextField();
        cmbPuesto = new javax.swing.JComboBox<>();
        cmbDepartamento = new javax.swing.JComboBox<>();
        rbMasculino = new javax.swing.JRadioButton();
        rbFemenino = new javax.swing.JRadioButton();
        tfSalario = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnLimpiar = new javax.swing.JButton();
        dcFechaIngreso = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbID.setText("ID Empelado");
        jPanel1.add(lbID, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 6, -1, -1));
        jPanel1.add(tfID, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 28, 488, -1));

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(527, 28, -1, -1));

        jLabel2.setText("Estado");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

        jLabel3.setText("Informacion Personal");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 91, -1, -1));

        jLabel4.setText("Informacion Laboral");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 91, -1, -1));

        lbNombre.setText("Nombre");
        jPanel1.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 137, -1, -1));

        lbApellidoP.setText("Apellido Paterno");
        jPanel1.add(lbApellidoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 209, -1, -1));

        lbApellidoM.setText("Apellido Materno");
        jPanel1.add(lbApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 287, -1, -1));

        lbDireccion.setText("Direccion");
        jPanel1.add(lbDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 365, -1, -1));

        lbSexo.setText("Sexo");
        jPanel1.add(lbSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 442, -1, -1));

        lbTel.setText("Telefono");
        jPanel1.add(lbTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 137, -1, -1));

        lbDepto.setText("Departamento");
        jPanel1.add(lbDepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 209, -1, -1));

        lbPuesto.setText("Puesto");
        jPanel1.add(lbPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 287, -1, -1));

        lbFecha.setText("Fecha de Ingreso");
        jPanel1.add(lbFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 370, -1, -1));

        lbSalario.setText("Salario");
        jPanel1.add(lbSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, -1, -1));
        jPanel1.add(tfNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 165, 270, -1));
        jPanel1.add(tfTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 260, -1));
        jPanel1.add(tfApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 270, -1));
        jPanel1.add(tfApellidoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 270, -1));
        jPanel1.add(tfDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 399, 270, -1));

        cmbPuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 321, 183, -1));

        cmbDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 243, 183, -1));

        buttonGroup1.add(rbMasculino);
        rbMasculino.setText("Masculino");
        jPanel1.add(rbMasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, -1));

        buttonGroup1.add(rbFemenino);
        rbFemenino.setText("Femenino");
        jPanel1.add(rbFemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 470, -1, -1));
        jPanel1.add(tfSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 460, 260, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, -1, -1));

        btnGuardar.setText("Guardar Cambios");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 580, -1, -1));

        jCheckBox1.setText("Pertenece a la Cooperativa");
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 510, -1, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 580, -1, -1));
        jPanel1.add(dcFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        // TODO add your handling code here:
         String id = tfID.getText().trim();
        if(id.isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID de empleado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            List<String> lineas = manejadorEmpleados.leerTodasLasLineas();
            String[] datosEmpleado = null;
            for(String linea : lineas){
                String[] partes = linea.split(",");
                if(partes.length > 0 && partes[0].trim().equals(id)){
                    datosEmpleado = partes;
                    break;
                }
            }

            if (datosEmpleado != null) {
                cargarDatosEmpleado(datosEmpleado);
                actualizarEstadoFormulario("MODIFICANDO");
            } else {
               
                actualizarEstadoFormulario("CREANDO");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de empleados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

         if(!validarCamposObligatorios()) return;

        try {
            if(!validarDatosExternos()) return;
            if(!validarLogicaCooperativa()) return;

            String id = tfID.getText().trim();
            String lineaDatos = construirLineaDeDatos(); 

            if(estadoFormulario.equals("CREANDO")){
                manejadorEmpleados.agregarLinea(lineaDatos);
                JOptionPane.showMessageDialog(this, "Empleado creado exitosamente.");
            } else { 
                manejadorEmpleados.modificarLinea(id, lineaDatos, 0);
                JOptionPane.showMessageDialog(this, "Empleado modificado exitosamente.");
            }
            
            actualizarEstadoFormulario("BUSCANDO");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
         actualizarEstadoFormulario("BUSCANDO");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mnEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mnEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mnEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mnEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mnEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVerificar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbDepartamento;
    private javax.swing.JComboBox<String> cmbPuesto;
    private com.toedter.calendar.JDateChooser dcFechaIngreso;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbApellidoM;
    private javax.swing.JLabel lbApellidoP;
    private javax.swing.JLabel lbDepto;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbPuesto;
    private javax.swing.JLabel lbSalario;
    private javax.swing.JLabel lbSexo;
    private javax.swing.JLabel lbTel;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTextField tfApellidoM;
    private javax.swing.JTextField tfApellidoP;
    private javax.swing.JTextField tfDireccion;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfSalario;
    private javax.swing.JTextField tfTel;
    // End of variables declaration//GEN-END:variables

}