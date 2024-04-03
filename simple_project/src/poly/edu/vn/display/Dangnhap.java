package poly.edu.vn.display;

import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;
import poly.edu.vn.display.other.DialogHelper;
import poly.edu.vn.display.other.NhanVien;
import poly.edu.vn.display.other.NhanvienDao;
import poly.edu.vn.display.other.ShareHelper;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *z
 * @author Admin
 */
public class Dangnhap extends javax.swing.JDialog {
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_ME_KEY = "rememberMe";
   NhanvienDao dao = new NhanvienDao();
   void init(){ 
        
    } 
    void login() { 
        String manv = txtMaNV.getText(); 
        String matKhau = new String(txtMatKhau.getPassword()); 
        try { 
            NhanVien nhanVien = dao.findById(manv); 
            if(nhanVien != null){ 
                String matKhau2 = nhanVien.getMatKhau(); 
                if(matKhau.equals(matKhau2)){ 
                    ShareHelper.USER = nhanVien; 
                    DialogHelper.alert(this, "Đăng nhập thành công!"); 
                    this.dispose(); 
                } 
                else{ 
                    DialogHelper.alert(this, "Sai mật khẩu!"); 
                } 
            } 
            else{ 
                DialogHelper.alert(this, "Sai tên đăng nhập!"); 
            } 
        }  
        catch (Exception e) { 
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!"); 
        } 
    } 
    void exit(){ 
        if(DialogHelper.confirm(this, "Bạn có muốn thoát khỏi ứng dụng không?")){ 
            System.exit(0); 
        }}
    /**
     * Creates new form Dangnhap1
     */
    public Dangnhap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        if (isRememberMeSelected()) {
            autoFillFields();
        }
    }
    private void handleRememberMe() {
        // Store or remove user credentials based on the "Remember Me" checkbox state
        if (chk_remember.isSelected()) {
            storeCredentials();
        } else {
            clearStoredCredentials();
        }
    }
    
    private void storeCredentials() {
        // Store username and password securely
        Preferences prefs = Preferences.userNodeForPackage(Dangnhap.class);
        prefs.put(USERNAME_KEY, getUsername());
        prefs.put(PASSWORD_KEY, getPassword());
        prefs.putBoolean(REMEMBER_ME_KEY, true);
    }
    private void clearStoredCredentials() {
        // Remove stored credentials
        Preferences prefs = Preferences.userNodeForPackage(Dangnhap.class);
        prefs.remove(USERNAME_KEY);
        prefs.remove(PASSWORD_KEY);
        prefs.putBoolean(REMEMBER_ME_KEY, false);
    }
    private void autoFillFields() {
        // Retrieve stored credentials and auto-fill the fields
        Preferences prefs = Preferences.userNodeForPackage(Dangnhap.class);
        txtMaNV.setText(prefs.get(USERNAME_KEY, ""));
        txtMatKhau.setText(prefs.get(PASSWORD_KEY, ""));
    }
    private boolean isRememberMeSelected() {
        // Check if "Remember Me" was selected in the last session
        Preferences prefs = Preferences.userNodeForPackage(Dangnhap.class);
        return prefs.getBoolean(REMEMBER_ME_KEY,true);
    }
    private String getUsername() {
        // Get the username from your username field
        return txtMaNV.getText();
    }
    private String getPassword() {
        // Get the password from your password field
        // Note: You should not store plain-text passwords; this is just for demonstration purposes.
        return new String(txtMatKhau.getPassword());
    }
    public void handlepressEnter(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            login();
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

        jLabel3 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        bttdangnhap = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        hienmk = new javax.swing.JCheckBox();
        chk_remember = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("HỆ THỐNG QUẢN LÝ ĐÀO TẠO");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Mật Khẩu");

        txtMaNV.setText("NV1");
        txtMaNV.setToolTipText("");
        txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaNVKeyPressed(evt);
            }
        });

        bttdangnhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/edu/vn/icon/User.png"))); // NOI18N
        bttdangnhap.setText("Đăng nhập");
        bttdangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttdangnhapActionPerformed(evt);
            }
        });
        bttdangnhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bttdangnhapKeyPressed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/edu/vn/icon/Log out.png"))); // NOI18N
        jButton2.setText("Kết thúc");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/edu/vn/images/secure (1).png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("ĐĂNG NHẬP");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Tên đăng nhập");

        txtMatKhau.setText("123456");
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyPressed(evt);
            }
        });

        hienmk.setText("Hiện mật khẩu");
        hienmk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hienmkActionPerformed(evt);
            }
        });

        chk_remember.setText("remember me?");
        chk_remember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_rememberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMatKhau)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bttdangnhap)
                                        .addGap(24, 24, 24)
                                        .addComponent(jButton2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(hienmk)
                                        .addGap(18, 18, 18)
                                        .addComponent(chk_remember, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hienmk)
                    .addComponent(chk_remember))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttdangnhap)
                    .addComponent(jButton2))
                .addGap(23, 23, 23))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttdangnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttdangnhapActionPerformed
        // TODO add your handling code here:
        this.login();
        handleRememberMe();
        
    }//GEN-LAST:event_bttdangnhapActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.exit();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bttdangnhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bttdangnhapKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_bttdangnhapKeyPressed

    private void txtMaNVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaNVKeyPressed
         // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            login();
            
        }
    }//GEN-LAST:event_txtMaNVKeyPressed

    private void txtMatKhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyPressed
         // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            login();
            
        }
    }//GEN-LAST:event_txtMatKhauKeyPressed

    private void hienmkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hienmkActionPerformed
         // TODO add your handling code here:
         if (hienmk.isSelected()) {
            txtMatKhau.setEchoChar((char) 0);
        } else {
            txtMatKhau.setEchoChar('*');
        }
    }//GEN-LAST:event_hienmkActionPerformed

    private void chk_rememberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_rememberActionPerformed
        // TODO add your handling code here:
        handleRememberMe();
    }//GEN-LAST:event_chk_rememberActionPerformed

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
            java.util.logging.Logger.getLogger(Dangnhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dangnhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dangnhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dangnhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dangnhap dialog = new Dangnhap(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttdangnhap;
    private javax.swing.JCheckBox chk_remember;
    private javax.swing.JCheckBox hienmk;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    // End of variables declaration//GEN-END:variables
}
