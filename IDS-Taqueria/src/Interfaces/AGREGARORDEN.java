/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Clases.Ordenes;
import Clases.Ventas;
import DAOS.DAOORDENES;
import DAOS.DAOPRODUCTOS;
import DAOS.DAOVENTAS;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Brayan
 */
public class AGREGARORDEN extends javax.swing.JFrame {

    static int bandera = 0;

    public AGREGARORDEN(int bandera) throws SQLException {
        initComponents();
        AGREGARORDEN.bandera = bandera;
        DefaultTableModel modelo = (DefaultTableModel) tblVentas.getModel();
        modelo.setRowCount(0);
        if (bandera == 0) {
            int a = DAOORDENES.UltimaO();
            jLabel1.setText("ORDEN: " + (a + 1));
        } else {
            jLabel1.setText("ORDEN: " + bandera);
        }
        try {
            ArrayList<String> Productos = new DAOPRODUCTOS().consultarTodos();
            DefaultComboBoxModel modeloCat = (DefaultComboBoxModel) CbProducto.getModel();
            for (String Producto : Productos) {
                modeloCat.addElement(Producto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        actualizarTablaD();

        this.setLocationRelativeTo(null);
        this.setTitle("Ventas");
        //lblCliente.setText(cliente.getName());
    }
    DefaultTableModel model = new DefaultTableModel();

    public void actualizarTablaD() {
        if (bandera == 0) {
        } else {
            try {
                DefaultTableModel modelo = (DefaultTableModel) tblVentas.getModel();
                ArrayList<Ventas> listaVentas1 = new DAOVENTAS().consultarTodos(bandera);
                modelo.setRowCount(0);
                for (Ventas p : listaVentas1) {
                    Object[] fila = new Object[]{
                        p.getProducto(),
                        p.getCantidad(),
                        p.getPrecio(),
                        p.getSubtotal()
                    };
                    modelo.addRow(fila);
                }
                productos = new DAOVENTAS().ConsultarEditar(bandera);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CbProducto = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        SpCantidad = new javax.swing.JSpinner();
        BtnFinalizar = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnO = new javax.swing.JButton();
        btnE = new javax.swing.JButton();
        btnAd = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        CbL = new javax.swing.JComboBox<>();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Precio", "Cantidad", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tblVentas);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("VENTA: ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("PRODUCTO");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("CANTIDAD");

        BtnFinalizar.setBackground(new java.awt.Color(51, 51, 255));
        BtnFinalizar.setText("Finalizar Venta");
        BtnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFinalizarActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        btnO.setText("Ordenes");
        btnO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOActionPerformed(evt);
            }
        });
        jToolBar1.add(btnO);

        btnE.setBackground(new java.awt.Color(255, 51, 51));
        btnE.setText("Eliminar");
        btnE.setFocusable(false);
        btnE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnE.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEActionPerformed(evt);
            }
        });
        jToolBar1.add(btnE);

        btnAd.setBackground(new java.awt.Color(0, 255, 0));
        btnAd.setText("Agregar");
        btnAd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdActionPerformed(evt);
            }
        });

        jLabel4.setText("Cliente: ");

        lblCliente.setText("asdlf");

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("LUGAR");

        CbL.setEditable(true);
        CbL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DOMICILIO", "RESTAURANT" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BtnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SpCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAd, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SpCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(lblCliente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(CbL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     ArrayList<Ventas> listaVentas = new ArrayList<>();
    int contador = 0;

    int indexTabla = 0;

    ArrayList<String> productos = new ArrayList<>();

    boolean flag = true;

    private void btnAdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdActionPerformed
        if (bandera == 0) {
        } else {
            this.productos = new DAOVENTAS().ConsultarEditar(bandera);
        }
        if ((int) SpCantidad.getValue() <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe de ser mayor a 0");
        } else {
            if (productos.isEmpty()) {
                productos.add(CbProducto.getSelectedItem().toString());
                try {
                    DefaultTableModel modelo = (DefaultTableModel) tblVentas.getModel();
                    double Precio = new DAOVENTAS().PyS((String) CbProducto.getSelectedItem());
                    int cantidad = (int) SpCantidad.getValue();
                    double subtotal = cantidad * Precio;
                    Object[] fila = new Object[]{
                        CbProducto.getSelectedItem(), Precio, SpCantidad.getValue(), subtotal};

                    String a = (String) CbProducto.getSelectedItem();
                    int b = (int) SpCantidad.getValue();
                    double c = Precio,
                            d = subtotal;
                    Ventas n = new Ventas(a, (int) c, b, d);

                    listaVentas.add(n);
                    contador++;
                    modelo.addRow(fila);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            } else {
                for (int i = 0; i < productos.size(); i++) {
                    System.out.println(productos.get(i));
                    if (!CbProducto.getSelectedItem().toString().equals(productos.get(i))) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    productos.add(CbProducto.getSelectedItem().toString());
                    try {
                        DefaultTableModel modelo = (DefaultTableModel) tblVentas.getModel();
                        double Precio = new DAOVENTAS().PyS((String) CbProducto.getSelectedItem());
                        int cantidad = (int) SpCantidad.getValue();
                        double subtotal = cantidad * Precio;
                        Object[] fila = new Object[]{
                            CbProducto.getSelectedItem(), Precio, SpCantidad.getValue(), subtotal};
                        String a = (String) CbProducto.getSelectedItem();
                        int b = (int) SpCantidad.getValue();
                        double c = Precio, d = subtotal;
                        Ventas n = new Ventas(a, (int) c, b, d);
                        listaVentas.add(n);
                        contador++;
                        modelo.addRow(fila);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Producto duplicado debe eliminarlo de la tabla e ingresarlo modificado");
                }

            }
        }

    }//GEN-LAST:event_btnAdActionPerformed

    private void BtnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFinalizarActionPerformed
        int fila = 0;        
        if (bandera != 0) {
            fila = JOptionPane.showConfirmDialog(this, "¿EL LUGAR DEL PEDIDO ES EL CORRECTO?");
        }
        if (JOptionPane.YES_OPTION == fila) {
            try {
                DefaultComboBoxModel modeloCat = (DefaultComboBoxModel) CbL.getModel();
                String a = (String) modeloCat.getSelectedItem();
                Ordenes obj = new Ordenes(1, 2, String.valueOf(LocalDate.now()), a);

                if (new DAOVENTAS().agregarVarios(listaVentas, obj, bandera)) {
                    JOptionPane.showMessageDialog(this, "La transacción se realizo con exito");
                }
            } catch (Exception ex) {
                //Logger.getLogger(AGREGARORDEN.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex);
            }
            new ORDENES().setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_BtnFinalizarActionPerformed

    private void btnOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOActionPerformed
        new ORDENES().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnOActionPerformed


    private void btnEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEActionPerformed
        String a = "";
        if (tblVentas.getSelectedRow() >= 0 && tblVentas.getSelectedRowCount() < 2) {
            DefaultTableModel mdl = (DefaultTableModel) tblVentas.getModel();
            int fila = JOptionPane.showConfirmDialog(this, "¿Deseas borrar este producto?", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            if (fila == JOptionPane.YES_OPTION) {
                a = (String) mdl.getValueAt(tblVentas.getSelectedRow(), 0);
                double b = (double) mdl.getValueAt(tblVentas.getSelectedRow(), 1);
                double c = (double) (int) mdl.getValueAt(tblVentas.getSelectedRow(), 2);
                double d = (double) mdl.getValueAt(tblVentas.getSelectedRow(), 3);
                Ventas j = new Ventas(0, a, b, c, d);
                mdl.removeRow(tblVentas.getSelectedRow());
                boolean f = false;
                if (bandera == 0) {
                    for (int i = 0; i < listaVentas.size(); i++) {
                        Ventas obj = listaVentas.get(i);
                        if (obj.getProducto().equals(j.getProducto()) && obj.getCantidad() == j.getCantidad()
                                && obj.getPrecio() == j.getPrecio() && obj.getSubtotal() == j.getSubtotal()) {
                            f = true;
                            listaVentas.remove(i);
                            productos.remove(i);
                        }
                    }
                    if (f) {
                        JOptionPane.showMessageDialog(this, "EL PRODUCTO SE ELIMINO CORRECTAMENTE");
                    } else {
                        JOptionPane.showMessageDialog(this, "EL PRODUCTO NO SE ELIMINO CORRECTAMENTE");
                    }
                }
            }
            if (bandera != 0) {
                try {
                    boolean B = new DAOVENTAS().BorrarProducto(a, bandera);
                    if (B) {
                        actualizarTablaD();
                        JOptionPane.showMessageDialog(this, "EL PRODUCTO SE ELIMINO CORRECTAMENTE");
                    } else {
                        JOptionPane.showMessageDialog(this, "EL PRODUCTO NO SE ELIMINO CORRECTAMENTE");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
            }
        } else if (tblVentas.getSelectedRow() >= 2) {
            JOptionPane.showMessageDialog(this, "SELECCIONE UN PRODUCTO", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "SOLO PUEDE ELIMINARSE UN PRODUCTO A LA VEZ", "Error", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnEActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new AGREGARORDEN(bandera).setVisible(true);
                } catch (SQLException e) {
                }
            }
        }
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnFinalizar;
    private javax.swing.JComboBox<String> CbL;
    private javax.swing.JComboBox<String> CbProducto;
    private javax.swing.JSpinner SpCantidad;
    private javax.swing.JButton btnAd;
    private javax.swing.JButton btnE;
    private javax.swing.JButton btnO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JTable tblVentas;
    // End of variables declaration//GEN-END:variables
}