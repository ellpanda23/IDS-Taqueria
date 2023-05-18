/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Clases.Facturas;
import DAOS.DAOFACTURAS;
import DAOS.DAOVENTAS;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author isack
 */
public class ReporteDetallado extends javax.swing.JFrame {

    /**
     * Creates new form ReporteDetallado
     */
    public ReporteDetallado() {
        initComponents();
        cargarTabla();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtHasta = new javax.swing.JTextField();
        txtDesde = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        btnGenerar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Desde:");

        jLabel2.setText("Hasta:");

        txtHasta.setText("dd/mm/aaaa");
        txtHasta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHastaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHastaFocusLost(evt);
            }
        });
        txtHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHastaKeyTyped(evt);
            }
        });

        txtDesde.setText("dd/mm/aaaa");
        txtDesde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDesdeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDesdeFocusLost(evt);
            }
        });
        txtDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDesdeKeyTyped(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/searchIcon.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtBuscar.setText("Buscar venta");
        txtBuscar.setMargin(new java.awt.Insets(2, 45, 2, 6));
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 220, 50));

        btnBuscar.setBackground(new java.awt.Color(51, 102, 255));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Factura", "Fecha", "Empleado", "Cliente", "Precio", "Detalles"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblReporte);
        if (tblReporte.getColumnModel().getColumnCount() > 0) {
            tblReporte.getColumnModel().getColumn(0).setResizable(false);
            tblReporte.getColumnModel().getColumn(0).setHeaderValue("Factura");
            tblReporte.getColumnModel().getColumn(1).setResizable(false);
            tblReporte.getColumnModel().getColumn(1).setHeaderValue("Fecha");
            tblReporte.getColumnModel().getColumn(2).setResizable(false);
            tblReporte.getColumnModel().getColumn(2).setHeaderValue("Empleado");
            tblReporte.getColumnModel().getColumn(3).setResizable(false);
            tblReporte.getColumnModel().getColumn(3).setHeaderValue("Cliente");
            tblReporte.getColumnModel().getColumn(4).setResizable(false);
            tblReporte.getColumnModel().getColumn(4).setHeaderValue("Precio");
            tblReporte.getColumnModel().getColumn(5).setResizable(false);
            tblReporte.getColumnModel().getColumn(5).setHeaderValue("Detalles");
        }

        btnGenerar.setBackground(new java.awt.Color(255, 51, 51));
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setText("Generar Reporte");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(51, 102, 255));
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarTabla()
    {
        try {
            // Crear el objeto de DAOFacturas
            DAOFACTURAS daoFacturas = new DAOFACTURAS();
            // Obtener los datos de la base de datos
            ArrayList<Facturas> listaFacturas = daoFacturas.consultarTodos();

            // Crear un nuevo modelo de tabla con los datos de la consulta
            String[] columnas = {"Factura", "Fecha", "Empleado", "Cliente", "Precio", "Detalles"};
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
            for (Facturas factura : listaFacturas) {
                Object[] fila = {
                    factura.getFacid(),
                    factura.getFecha(),
                    factura.getEmpleado().getNombre(),
                    factura.getCliente().getNombre(),
                    factura.getTotal(),
                    "Detalles"
                };
                modelo.addRow(fila);
            }

            // Establecer el nuevo modelo de tabla en la tabla
            tblReporte.setModel(modelo);

        } catch (Exception ex) {
            // Manejar la excepción adecuadamente
            ex.printStackTrace();
        }
    }

    
    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void txtDesdeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDesdeFocusGained
        if(txtDesde.getText().matches("dd/mm/aaaa"))
            txtDesde.setText("");
        else
            txtDesde.selectAll();
    }//GEN-LAST:event_txtDesdeFocusGained

    private void txtDesdeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesdeKeyTyped
        // Borra la selección actual si existe
        txtDesde.replaceSelection("");
        
        char c = evt.getKeyChar();

        // Permitir solo números
        if (!Character.isDigit(c)) {
            evt.consume();
        }

        // Agregar el carácter separador "/" automáticamente después de ingresar el segundo y quinto carácter
        String text = txtDesde.getText();
        int length = text.length();
        if ((length == 2 || length == 5) && !"/".equals(text.substring(length-1))) {
            txtDesde.setText(text + "/");
        }

        // Restringir el formato de la fecha a dd/mm/aaaa
        if (length >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDesdeKeyTyped

    private void txtDesdeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDesdeFocusLost
        if(txtDesde.getText().matches(""))
            txtDesde.setText("dd/mm/aaaa");
    }//GEN-LAST:event_txtDesdeFocusLost

    private void txtHastaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHastaFocusGained
        if(txtHasta.getText().matches("dd/mm/aaaa"))
            txtHasta.setText("");
        else
            txtHasta.selectAll();
    }//GEN-LAST:event_txtHastaFocusGained

    private void txtHastaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHastaFocusLost
        if(txtHasta.getText().matches(""))
            txtHasta.setText("dd/mm/aaaa");
    }//GEN-LAST:event_txtHastaFocusLost

    private void txtHastaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHastaKeyTyped
        // Borra la selección actual si existe
        txtHasta.replaceSelection("");
        
        char c = evt.getKeyChar();

        // Permitir solo números
        if (!Character.isDigit(c)) {
            evt.consume();
        }

        // Agregar el carácter separador "/" automáticamente después de ingresar el segundo y quinto carácter
        String text = txtHasta.getText();
        int length = text.length();
        if ((length == 2 || length == 5) && !"/".equals(text.substring(length-1))) {
            txtHasta.setText(text + "/");
        }

        // Restringir el formato de la fecha a dd/mm/aaaa
        if (length >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtHastaKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(txtDesde.getText().equals("dd/mm/aaaa") && txtHasta.getText().equals("dd/mm/aaaa") && txtBuscar.getText().equals("Buscar venta") ) {
            // CUANDO ESTAN VACIOS TODOS LOS TEXTBOX SE CARGAN TODAS LAS FACTURAS
            //JOptionPane.showMessageDialog(null, "Se debe ingresar un rango de fechas o buscar una venta por numero de factura, empleado, fecha o cliente", "Error", JOptionPane.ERROR_MESSAGE);
            cargarTabla();
        } 
        else if(txtDesde.getText().equals("dd/mm/aaaa") && txtHasta.getText().equals("dd/mm/aaaa") && !txtBuscar.getText().equals("Buscar venta")) {
            // ESTO LO HACE CUANDO SOLO TIENE TEXTO EL TEXTBOX DE BUSCAR
            try {
                // Crear el objeto de DAOFacturas
                DAOFACTURAS daoFacturas = new DAOFACTURAS();
                // Obtener los datos de la base de datos
                ArrayList<Facturas> listaFacturas = daoFacturas.buscarFacturas(txtBuscar.getText());

                // Crear un nuevo modelo de tabla con los datos de la consulta
                String[] columnas = {"Factura", "Fecha", "Empleado", "Cliente", "Precio", "Detalles"};
                DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
                for (Facturas factura : listaFacturas) {
                    Object[] fila = {
                        factura.getFacid(),
                        factura.getFecha(),
                        factura.getEmpleado().getNombre(),
                        factura.getCliente().getNombre(),
                        factura.getTotal(),
                        "Detalles"
                    };
                    modelo.addRow(fila);
                }

                // Establecer el nuevo modelo de tabla en la tabla
                tblReporte.setModel(modelo);

            } catch (Exception ex) {
                // Manejar la excepción adecuadamente
                ex.printStackTrace();
            }

        }
        else if(!txtDesde.getText().equals("dd/mm/aaaa") && !txtHasta.getText().equals("dd/mm/aaaa") && txtBuscar.getText().equals("Buscar venta")) 
        {
            // ESTE ENTRA CUANDO AMBOS TEXTBOX TIENEN UN RANGO DE FECHA
            try {
                // Crear el objeto de DAOFacturas
                DAOFACTURAS daoFacturas = new DAOFACTURAS();
                
                // Convertir el texto en tipo date

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date desde = formato.parse(txtDesde.getText());
                java.util.Date hasta = formato.parse(txtHasta.getText());

                
                // Obtener los datos de la base de datos
                ArrayList<Facturas> listaFacturas = daoFacturas.consultarDesdeHasta(desde, hasta);

                // Crear un nuevo modelo de tabla con los datos de la consulta
                String[] columnas = {"Factura", "Fecha", "Empleado", "Cliente", "Precio", "Detalles"};
                DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
                for (Facturas factura : listaFacturas) {
                    Object[] fila = {
                        factura.getFacid(),
                        factura.getFecha(),
                        factura.getEmpleado().getNombre(),
                        factura.getCliente().getNombre(),
                        factura.getTotal(),
                        "Detalles"
                    };
                    modelo.addRow(fila);
                }

                // Establecer el nuevo modelo de tabla en la tabla
                tblReporte.setModel(modelo);

            } catch (Exception ex) {
                // Manejar la excepción adecuadamente
                ex.printStackTrace();
            }
        }

            
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        if(txtBuscar.getText().matches("Buscar venta"))
            txtBuscar.setText("");
        else
            txtBuscar.selectAll();
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        if(txtBuscar.getText().matches(""))
            txtBuscar.setText("Buscar venta");
    }//GEN-LAST:event_txtBuscarFocusLost

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
            java.util.logging.Logger.getLogger(ReporteDetallado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteDetallado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteDetallado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteDetallado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteDetallado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReporte;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    // End of variables declaration//GEN-END:variables
}
