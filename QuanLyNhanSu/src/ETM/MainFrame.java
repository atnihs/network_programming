package ETM;

import Giaodien.*;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import XyLyDuLieu.Employee;
import ETM.Main;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class MainFrame extends javax.swing.JFrame {

    private Employee curEmp = null;
    public static String Ipconfig = null;

    /**
     * Tạo mới from MainFrame
     */
    public MainFrame(String isAdmin) {
        // this.curEmp = curEmp;

        initComponents();
        setFrameStyle();

        if (isAdmin.equalsIgnoreCase("1")) {
            setAdminLayout();
        }//Nếu người dùng là Admin
        else {
            setUserLayout();
        }//Người dùng bình thường

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrlPaneLeftUser = new javax.swing.JScrollPane();
        pnlLeftEmp = new javax.swing.JPanel();
        btnEmpMyProfile = new javax.swing.JButton();
        btnLogout1 = new javax.swing.JButton();
        btnEmpTransferManage = new javax.swing.JButton();
        btnRequestTransfer = new javax.swing.JButton();
        btnHomeEmp = new javax.swing.JButton();
        btnDepartmentList = new javax.swing.JButton();
        btnProjectList = new javax.swing.JButton();
        btnLocationList = new javax.swing.JButton();
        spltPane = new javax.swing.JSplitPane();
        scrlpaneLeftAdmin = new javax.swing.JScrollPane();
        pnlLeft = new javax.swing.JPanel();
        btnProjectManage = new javax.swing.JButton();
        btnDepartmentManage = new javax.swing.JButton();
        btnLocationManage = new javax.swing.JButton();
        btnEmployeeManage = new javax.swing.JButton();
        pnlHome = new javax.swing.JPanel();
        menuBarMain = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        mnuiLogout = new javax.swing.JMenuItem();
        mnuiExit = new javax.swing.JMenuItem();

        scrlPaneLeftUser.setMaximumSize(null);
        scrlPaneLeftUser.setMinimumSize(null);
        scrlPaneLeftUser.setPreferredSize(new java.awt.Dimension(225, 235));

        pnlLeftEmp.setMaximumSize(new java.awt.Dimension(185, 539));
        pnlLeftEmp.setMinimumSize(new java.awt.Dimension(185, 539));
        pnlLeftEmp.setPreferredSize(new java.awt.Dimension(185, 539));

        btnEmpMyProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/project_open.png"))); // NOI18N
        btnEmpMyProfile.setText("My Profile");
        btnEmpMyProfile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmpMyProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpMyProfileActionPerformed(evt);
            }
        });

        btnLogout1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        btnLogout1.setText("Logout");
        btnLogout1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogout1ActionPerformed(evt);
            }
        });

        btnEmpTransferManage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transfermanage.png"))); // NOI18N
        btnEmpTransferManage.setText("My Transfers Manage");
        btnEmpTransferManage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmpTransferManage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpTransferManageActionPerformed(evt);
            }
        });

        btnRequestTransfer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/transferNew.png"))); // NOI18N
        btnRequestTransfer.setText("Request New Transfer");
        btnRequestTransfer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRequestTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestTransferActionPerformed(evt);
            }
        });

        btnHomeEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        btnHomeEmp.setText("Home");
        btnHomeEmp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHomeEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeEmpActionPerformed(evt);
            }
        });

        btnDepartmentList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Department.png"))); // NOI18N
        btnDepartmentList.setText("Departments List");
        btnDepartmentList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDepartmentList.setMaximumSize(new java.awt.Dimension(101, 41));
        btnDepartmentList.setMinimumSize(new java.awt.Dimension(101, 41));
        btnDepartmentList.setPreferredSize(new java.awt.Dimension(79, 25));
        btnDepartmentList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepartmentListActionPerformed(evt);
            }
        });

        btnProjectList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/project_open.png"))); // NOI18N
        btnProjectList.setText("Projects List");
        btnProjectList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProjectList.setMaximumSize(new java.awt.Dimension(101, 41));
        btnProjectList.setMinimumSize(new java.awt.Dimension(101, 41));
        btnProjectList.setPreferredSize(new java.awt.Dimension(79, 25));
        btnProjectList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProjectListActionPerformed(evt);
            }
        });

        btnLocationList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Location.png"))); // NOI18N
        btnLocationList.setText("Locations List");
        btnLocationList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLocationList.setMaximumSize(new java.awt.Dimension(101, 41));
        btnLocationList.setMinimumSize(new java.awt.Dimension(101, 41));
        btnLocationList.setPreferredSize(new java.awt.Dimension(79, 25));
        btnLocationList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocationListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftEmpLayout = new javax.swing.GroupLayout(pnlLeftEmp);
        pnlLeftEmp.setLayout(pnlLeftEmpLayout);
        pnlLeftEmpLayout.setHorizontalGroup(
            pnlLeftEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHomeEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRequestTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmpTransferManage, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLeftEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnProjectList, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDepartmentList, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLocationList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(pnlLeftEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEmpMyProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pnlLeftEmpLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnDepartmentList, btnEmpMyProfile, btnEmpTransferManage, btnHomeEmp, btnLocationList, btnLogout1, btnProjectList, btnRequestTransfer});

        pnlLeftEmpLayout.setVerticalGroup(
            pnlLeftEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHomeEmp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRequestTransfer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmpTransferManage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProjectList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDepartmentList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLocationList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmpMyProfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout1)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        pnlLeftEmpLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnDepartmentList, btnEmpMyProfile, btnEmpTransferManage, btnHomeEmp, btnLocationList, btnLogout1, btnProjectList, btnRequestTransfer});

        scrlPaneLeftUser.setViewportView(pnlLeftEmp);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Employee Transfer Management");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        spltPane.setDividerLocation(220);
        spltPane.setDividerSize(0);
        spltPane.setEnabled(false);
        spltPane.setMaximumSize(null);
        spltPane.setPreferredSize(new java.awt.Dimension(1024, 543));

        scrlpaneLeftAdmin.setMaximumSize(null);
        scrlpaneLeftAdmin.setMinimumSize(null);
        scrlpaneLeftAdmin.setPreferredSize(new java.awt.Dimension(219, 541));

        pnlLeft.setMaximumSize(new java.awt.Dimension(185, 539));
        pnlLeft.setMinimumSize(new java.awt.Dimension(185, 539));
        pnlLeft.setPreferredSize(new java.awt.Dimension(185, 539));

        btnProjectManage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/project_open.png"))); // NOI18N
        btnProjectManage.setText("Quản lý dự án");
        btnProjectManage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProjectManage.setMaximumSize(new java.awt.Dimension(101, 41));
        btnProjectManage.setMinimumSize(new java.awt.Dimension(101, 41));
        btnProjectManage.setPreferredSize(new java.awt.Dimension(79, 25));
        btnProjectManage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProjectManageActionPerformed(evt);
            }
        });

        btnDepartmentManage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Department.png"))); // NOI18N
        btnDepartmentManage.setText("Quản lý phòng ban");
        btnDepartmentManage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDepartmentManage.setMaximumSize(new java.awt.Dimension(101, 41));
        btnDepartmentManage.setMinimumSize(new java.awt.Dimension(101, 41));
        btnDepartmentManage.setPreferredSize(new java.awt.Dimension(79, 25));
        btnDepartmentManage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepartmentManageActionPerformed(evt);
            }
        });

        btnLocationManage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Location.png"))); // NOI18N
        btnLocationManage.setText("Quản lý địa chỉ");
        btnLocationManage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLocationManage.setMaximumSize(new java.awt.Dimension(101, 41));
        btnLocationManage.setMinimumSize(new java.awt.Dimension(101, 41));
        btnLocationManage.setPreferredSize(new java.awt.Dimension(79, 25));
        btnLocationManage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocationManageActionPerformed(evt);
            }
        });

        btnEmployeeManage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/employee.png"))); // NOI18N
        btnEmployeeManage.setText("Quản lý nhân viên");
        btnEmployeeManage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmployeeManage.setMaximumSize(new java.awt.Dimension(101, 41));
        btnEmployeeManage.setMinimumSize(new java.awt.Dimension(101, 41));
        btnEmployeeManage.setPreferredSize(new java.awt.Dimension(79, 25));
        btnEmployeeManage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeManageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnProjectManage, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnDepartmentManage, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnLocationManage, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnEmployeeManage, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(btnEmployeeManage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProjectManage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDepartmentManage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btnLocationManage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
        );

        pnlLeftLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnDepartmentManage, btnEmployeeManage, btnLocationManage, btnProjectManage});

        scrlpaneLeftAdmin.setViewportView(pnlLeft);

        spltPane.setLeftComponent(scrlpaneLeftAdmin);

        pnlHome.setMaximumSize(null);
        pnlHome.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pnlHomeLayout = new javax.swing.GroupLayout(pnlHome);
        pnlHome.setLayout(pnlHomeLayout);
        pnlHomeLayout.setHorizontalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 803, Short.MAX_VALUE)
        );
        pnlHomeLayout.setVerticalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        spltPane.setRightComponent(pnlHome);

        getContentPane().add(spltPane, java.awt.BorderLayout.CENTER);

        menuFile.setText("File");

        mnuiLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        mnuiLogout.setText("Logout");
        mnuiLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiLogoutActionPerformed(evt);
            }
        });
        menuFile.add(mnuiLogout);

        mnuiExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_delete.png"))); // NOI18N
        mnuiExit.setText("Exit Application");
        mnuiExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiExitActionPerformed(evt);
            }
        });
        menuFile.add(mnuiExit);

        menuBarMain.add(menuFile);

        setJMenuBar(menuBarMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLocationManageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocationManageActionPerformed
        PnlAdminLocationManage pnlLocationManage = new PnlAdminLocationManage();
        pnlLocationManage.IpAddress = Ipconfig;
        spltPane.setRightComponent(pnlLocationManage);
    }//GEN-LAST:event_btnLocationManageActionPerformed

    private void btnDepartmentManageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepartmentManageActionPerformed
        PnlAdminDepartmentManage pnlDepartmentManage = new PnlAdminDepartmentManage();
        //PnlAdminDepartmentManage depm=new PnlAdminDepartmentManage();
        pnlDepartmentManage.IpAddress = Ipconfig;
        spltPane.setRightComponent(pnlDepartmentManage);
    }//GEN-LAST:event_btnDepartmentManageActionPerformed

    private void mnuiLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiLogoutActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát khỏi hệ thống ?", "Thoát", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
            LoginFrame login = new LoginFrame();
            this.setVisible(false);
            login.lblAddress.setText(Ipconfig);
            login.setVisible(true);
        }
    }//GEN-LAST:event_mnuiLogoutActionPerformed

    //Thoát khỏi ứng dụng
    private void mnuiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiExitActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn thực sự muốn thoát khỏi ?", "Đã thoát", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_mnuiExitActionPerformed

    private void btnEmployeeManageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeManageActionPerformed
        PnlAdminEmployeeManage pnlEmp = new PnlAdminEmployeeManage();
        pnlEmp.IpAddress = Ipconfig;
        spltPane.setRightComponent(pnlEmp);
    }//GEN-LAST:event_btnEmployeeManageActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Main.frmLogin.setVisible(true);

    }//GEN-LAST:event_formWindowClosing

    private void btnProjectManageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjectManageActionPerformed
        PnlAdminProjectManage pnlPrj = new PnlAdminProjectManage();
        pnlPrj.IpAddress = Ipconfig;
        spltPane.setRightComponent(pnlPrj);
    }//GEN-LAST:event_btnProjectManageActionPerformed

    private void btnEmpTransferManageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpTransferManageActionPerformed


    }//GEN-LAST:event_btnEmpTransferManageActionPerformed

    private void btnRequestTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequestTransferActionPerformed


    }//GEN-LAST:event_btnRequestTransferActionPerformed

    private void btnHomeEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeEmpActionPerformed

    }//GEN-LAST:event_btnHomeEmpActionPerformed

    private void btnDepartmentListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepartmentListActionPerformed
        PnlUserDepartmentList deptEmpPnl = new PnlUserDepartmentList();
        spltPane.setRightComponent(deptEmpPnl);
    }//GEN-LAST:event_btnDepartmentListActionPerformed

    private void btnProjectListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjectListActionPerformed

    }//GEN-LAST:event_btnProjectListActionPerformed

    private void btnLocationListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocationListActionPerformed
        //PnlUserLocationList empLocLst = new PnlUserLocationList();
        //spltPane.setRightComponent(empLocLst);
    }//GEN-LAST:event_btnLocationListActionPerformed

    private void btnLogout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát khỏi hệ thống ?", "Thoát", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
            LoginFrame login = new LoginFrame();
            this.setVisible(false);
            login.lblAddress.setText(Ipconfig);
            login.setVisible(true);
        }

    }//GEN-LAST:event_btnLogout1ActionPerformed

    private void btnEmpMyProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpMyProfileActionPerformed

    }//GEN-LAST:event_btnEmpMyProfileActionPerformed

    private void btnChamCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChamCongActionPerformed
        // TODO add your handling code here:
       // PnlChamCongManager pnlchamcong = new PnlChamCongManager();
       // pnlchamcong.IpAddress = Ipconfig;
        //spltPane.setRightComponent(pnlchamcong);
    }//GEN-LAST:event_btnChamCongActionPerformed

    /*Xác nhận và đăng nhập hệ thống*/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame(null).setVisible(true);
            }
        });
    }

    /*Quản trị*/
    private void setAdminLayout() {
        scrlPaneLeftUser.setVisible(false);//ẩn bảng người dùng

        scrlpaneLeftAdmin.setVisible(true);//hiện và thêm của người dùng
        spltPane.setLeftComponent(scrlpaneLeftAdmin);

        this.setTitle(this.getTitle() + " - SYSTEM ADMINISTRATOR");
    }

    /*Phong cửa sổ bình thường*/
    private void setUserLayout() {
        scrlpaneLeftAdmin.setVisible(false);//ẩn bảng Admin

        scrlPaneLeftUser.setVisible(true);
        spltPane.setLeftComponent(this.scrlPaneLeftUser);//hiện và thêm bảng người dùng

        this.setTitle(this.getTitle() + " - NORMAL USER");
    }
//kích thước frame

    private void setFrameStyle() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;

        setBounds((width - 1024) / 2, (height - 768) / 2, 1024, 768);

        setIconImage((new ImageIcon("wire_transfer.png")).getImage());

        setFont(new Font("Verdana", Font.BOLD, 10));
        spltPane.setRightComponent(pnlHome);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDepartmentList;
    private javax.swing.JButton btnDepartmentManage;
    private javax.swing.JButton btnEmpMyProfile;
    private javax.swing.JButton btnEmpTransferManage;
    private javax.swing.JButton btnEmployeeManage;
    private javax.swing.JButton btnHomeEmp;
    private javax.swing.JButton btnLocationList;
    private javax.swing.JButton btnLocationManage;
    private javax.swing.JButton btnLogout1;
    private javax.swing.JButton btnProjectList;
    private javax.swing.JButton btnProjectManage;
    private javax.swing.JButton btnRequestTransfer;
    private javax.swing.JMenuBar menuBarMain;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem mnuiExit;
    private javax.swing.JMenuItem mnuiLogout;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlLeftEmp;
    private javax.swing.JScrollPane scrlPaneLeftUser;
    private javax.swing.JScrollPane scrlpaneLeftAdmin;
    private javax.swing.JSplitPane spltPane;
    // End of variables declaration//GEN-END:variables
    private int EmployeeId;
}
