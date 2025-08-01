package Menu;

import Mantenimiento.mnUsuarios;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Menu extends javax.swing.JFrame {

   
    public Menu() {
        initComponents();
         this.setLocationRelativeTo(null);

    }
    
         public Menu(String nombreUsuario, int nivelDeAcceso) {
        initComponents();
        this.setLocationRelativeTo(null);
        lblBienvenido.setText("Bienvenido, " + nombreUsuario);
          ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/Iconos/icons8-add-user-male-50.png"));     
          ImageIcon iconoPequeño = redimensionarIcono(iconoOriginal, 20, 20);
          mnUsuarios.setIcon(iconoPequeño);
          
          aplicarPermisos(nivelDeAcceso);
         }
         
          private void aplicarPermisos(int nivel) {
        if (nivel == 1) {
            mnUsuarios.setEnabled(false);
            mnProcesos.setEnabled(false);
        }
    }
    
    public ImageIcon redimensionarIcono(ImageIcon iconoOriginal, int ancho, int alto) {
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
        
    }
    
    public Menu(int accessLevel) {
        initComponents(); 
        this.setLocationRelativeTo(null); 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCrearUsuario = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblBienvenido = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        mnMantenimientos = new javax.swing.JMenu();
        mnUsuarios = new javax.swing.JMenuItem();
        mnDepto = new javax.swing.JMenuItem();
        mnPuestos = new javax.swing.JMenuItem();
        mnEmpleados = new javax.swing.JMenuItem();
        mnProcesos = new javax.swing.JMenu();
        prcGenerarNomina = new javax.swing.JMenuItem();
        prcReversarNomina = new javax.swing.JMenuItem();
        mnConsultas = new javax.swing.JMenu();
        csDepartamento = new javax.swing.JMenuItem();
        csPuestos = new javax.swing.JMenuItem();
        csEmpleados = new javax.swing.JMenuItem();
        csEmpleadosporDept = new javax.swing.JMenuItem();
        csEmpleadosporPuest = new javax.swing.JMenuItem();
        csEmpleadosporFecha = new javax.swing.JMenuItem();
        csNominaporFecha = new javax.swing.JMenuItem();
        csNominaporId = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(PaletaDeColores.FONDO_PRINCIPAL_OSCURO);

        btnCrearUsuario.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnCrearUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8-add-user-male-50.png"))); // NOI18N
        btnCrearUsuario.setText("Nuevo Empleado");
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8-dollar-64.png"))); // NOI18N
        jButton2.setText("Calcular Nomina");

        jButton3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8-find-user-male-50.png"))); // NOI18N
        jButton3.setText("Buscar Empelado");

        lblBienvenido.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblBienvenido.setText("Bienvenido, ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblBienvenido)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCrearUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(156, 156, 156)
                        .addComponent(jButton3)
                        .addGap(79, 79, 79))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblBienvenido)
                .addGap(215, 215, 215)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(270, Short.MAX_VALUE))
        );

        jMenuBar.setBackground(PaletaDeColores.SUPERFICIE_GRAFITO);

        mnMantenimientos.setText("Mantenimientos");

        mnUsuarios.setBackground(PaletaDeColores.ACENTO_AZUL_ELECTRICO);
        mnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8-add-user-male-50.png"))); // NOI18N
        mnUsuarios.setText("De Usuarios");
        mnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnUsuariosActionPerformed(evt);
            }
        });
        mnMantenimientos.add(mnUsuarios);

        mnDepto.setText("De Departamento");
        mnDepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDeptoActionPerformed(evt);
            }
        });
        mnMantenimientos.add(mnDepto);

        mnPuestos.setText("De Puestos");
        mnMantenimientos.add(mnPuestos);

        mnEmpleados.setText("De Empleados");
        mnMantenimientos.add(mnEmpleados);

        jMenuBar.add(mnMantenimientos);

        mnProcesos.setText("Procesos");

        prcGenerarNomina.setText("Generar Nomina");
        mnProcesos.add(prcGenerarNomina);

        prcReversarNomina.setText("Revervar Nomina");
        mnProcesos.add(prcReversarNomina);

        jMenuBar.add(mnProcesos);

        mnConsultas.setText("Consultas");

        csDepartamento.setText("De Departamento");
        mnConsultas.add(csDepartamento);

        csPuestos.setText("De Puestos");
        mnConsultas.add(csPuestos);

        csEmpleados.setText("De Empleados");
        mnConsultas.add(csEmpleados);

        csEmpleadosporDept.setText("De Empleados por Departamento");
        mnConsultas.add(csEmpleadosporDept);

        csEmpleadosporPuest.setText("De Empleados por Puestos");
        mnConsultas.add(csEmpleadosporPuest);

        csEmpleadosporFecha.setText("De Empleados por Fecha de Ingreso");
        mnConsultas.add(csEmpleadosporFecha);

        csNominaporFecha.setText("De Nomina por Fecha");
        mnConsultas.add(csNominaporFecha);

        csNominaporId.setText("De Nomina por Id_Empleado");
        mnConsultas.add(csNominaporId);

        jMenuBar.add(mnConsultas);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnDeptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDeptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnDeptoActionPerformed

    private void mnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnUsuariosActionPerformed
    mnUsuarios mnUsuariosPrincipal = new mnUsuarios();
    mnUsuariosPrincipal.setVisible(true);
    }//GEN-LAST:event_mnUsuariosActionPerformed

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        // TODO add your handling code here:
         mnUsuarios mnUsuariosPrincipal = new mnUsuarios();
         mnUsuariosPrincipal.setVisible(true);
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JMenuItem csDepartamento;
    private javax.swing.JMenuItem csEmpleados;
    private javax.swing.JMenuItem csEmpleadosporDept;
    private javax.swing.JMenuItem csEmpleadosporFecha;
    private javax.swing.JMenuItem csEmpleadosporPuest;
    private javax.swing.JMenuItem csNominaporFecha;
    private javax.swing.JMenuItem csNominaporId;
    private javax.swing.JMenuItem csPuestos;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JMenu mnConsultas;
    private javax.swing.JMenuItem mnDepto;
    private javax.swing.JMenuItem mnEmpleados;
    private javax.swing.JMenu mnMantenimientos;
    private javax.swing.JMenu mnProcesos;
    private javax.swing.JMenuItem mnPuestos;
    private javax.swing.JMenuItem mnUsuarios;
    private javax.swing.JMenuItem prcGenerarNomina;
    private javax.swing.JMenuItem prcReversarNomina;
    // End of variables declaration//GEN-END:variables

}
