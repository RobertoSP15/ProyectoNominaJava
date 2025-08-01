
package Mantenimiento;

import ManejoDeArchivos.ManejoDeArchivos;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
/**
 *
 * @author germa
 */
public class mnUsuarios extends javax.swing.JFrame {
    
    private final ManejoDeArchivos manejador;
    private static final String NOMBRE_ARCHIVO = "usuarios.txt";

   public mnUsuarios() {
        this.manejador = new ManejoDeArchivos(NOMBRE_ARCHIVO);
        initComponents();
        configuracionInicial();   
        
        ImageIcon iconoOriginalBoton = new ImageIcon(getClass().getResource("/Iconos/icons8-exit-50.png"));
        ImageIcon iconoRedimensionado = redimensionarIcono(iconoOriginalBoton, 35, 35);
        btnCancelar.setIcon(iconoRedimensionado);
    }
   
   public ImageIcon redimensionarIcono(ImageIcon iconoOriginal, int ancho, int alto) {
    Image imagenOriginal = iconoOriginal.getImage();
    Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    return new ImageIcon(imagenRedimensionada);
}
    
    private void configuracionInicial() {
        rbUsuario.setSelected(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de Usuarios");
    }
    
    private boolean validarCampos() {
        if (tfUsuario.getText().trim().isEmpty() ||
            tfPass.getText().trim().isEmpty() ||
            tfNombre1.getText().trim().isEmpty() ||
            tfApellido.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Los campos Usuario, Contraseña, Nombre y Apellido son obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        tfUsuario.setText("");
        tfPass.setText("");
        tfNombre1.setText("");
        tfApellido.setText("");
        tfCorreo.setText("");
        rbUsuario.setSelected(true);
    }

    private String construirLineaDeDatos() {
        String nivelAcceso = rbAdmin.isSelected() ? "0" : "1";
        
        return nivelAcceso + "," +
               tfUsuario.getText().trim() + "," +
               tfPass.getText().trim() + "," + 
               tfNombre1.getText().trim() + "," +
               tfApellido.getText().trim() + "," +
               tfCorreo.getText().trim();
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel5 = new javax.swing.JLabel();
        Panel = new javax.swing.JPanel();
        lbUsuario = new javax.swing.JLabel();
        tfPass = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        tfApellido = new javax.swing.JTextField();
        tfNombre1 = new javax.swing.JTextField();
        lbApellido = new javax.swing.JLabel();
        lbCorreo = new javax.swing.JLabel();
        tfCorreo = new javax.swing.JTextField();
        lbAcceso = new javax.swing.JLabel();
        rbAdmin = new javax.swing.JRadioButton();
        rbUsuario = new javax.swing.JRadioButton();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel5.setText("*");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Panel.setBackground(PaletaDeColores.SUPERFICIE_GRAFITO);
        Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lbUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbUsuario.setForeground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbUsuario.setText("Usuario");
        Panel.add(lbUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));
        Panel.add(tfPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 370, 30));

        lbPass.setBackground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbPass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbPass.setForeground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbPass.setText("Contraseña");
        Panel.add(lbPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));
        Panel.add(tfUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 370, -1));

        lbNombre.setBackground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbNombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNombre.setForeground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbNombre.setText("Nombre");
        Panel.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));
        Panel.add(tfApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 370, -1));
        Panel.add(tfNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 370, -1));

        lbApellido.setBackground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbApellido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbApellido.setForeground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbApellido.setText("Apellido");
        Panel.add(lbApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        lbCorreo.setBackground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbCorreo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbCorreo.setForeground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbCorreo.setText("Correo");
        Panel.add(lbCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));
        Panel.add(tfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 370, -1));

        lbAcceso.setBackground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbAcceso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbAcceso.setForeground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        lbAcceso.setText("Nivel de Acceso");
        Panel.add(lbAcceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, -1, -1));

        buttonGroup1.add(rbAdmin);
        rbAdmin.setForeground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        rbAdmin.setText("Administrador");
        Panel.add(rbAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, -1, -1));

        buttonGroup1.add(rbUsuario);
        rbUsuario.setForeground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        rbUsuario.setText("Usuario");
        Panel.add(rbUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, -1, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setName(""); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        Panel.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 497, 80, 30));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        Panel.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 497, 80, 30));

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        Panel.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 497, 80, 30));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8-exit-50.png"))); // NOI18N
        btnCancelar.setBorder(null);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        Panel.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 40, 40));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");
        Panel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 30, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");
        Panel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 20, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");
        Panel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 30, -1));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*");
        Panel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 30, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");
        Panel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 30, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(PaletaDeColores.TEXTO_BLANCO_SUAVE);
        jLabel1.setText("MANTENIMIENTO USUARIO");
        Panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
         if (!validarCampos()) { return; }

        String usuario = tfUsuario.getText().trim();
        
        try {
           
            if (manejador.lineaExiste(usuario, 1)) {
                JOptionPane.showMessageDialog(this, "El login de usuario '" + usuario + "' ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String lineaNueva = construirLineaDeDatos();
            manejador.agregarLinea(lineaNueva);
            
            JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error de Archivo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
         String usuario = tfUsuario.getText().trim();
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el login de usuario para eliminar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar al usuario '" + usuario + "'?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {

                if (manejador.eliminarLinea(usuario, 1)) {
                    JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró al usuario '" + usuario + "'.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage(), "Error de Archivo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

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
            java.util.logging.Logger.getLogger(mnUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mnUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mnUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mnUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mnUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbAcceso;
    private javax.swing.JLabel lbApellido;
    private javax.swing.JLabel lbCorreo;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JRadioButton rbAdmin;
    private javax.swing.JRadioButton rbUsuario;
    private javax.swing.JTextField tfApellido;
    private javax.swing.JTextField tfCorreo;
    private javax.swing.JTextField tfNombre1;
    private javax.swing.JTextField tfPass;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}
