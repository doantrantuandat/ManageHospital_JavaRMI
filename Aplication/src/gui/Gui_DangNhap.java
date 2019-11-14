package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import daos.DangNhapDaos;
import htqlbv_entities.NhanVien;


public class Gui_DangNhap extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbllogOn,lbluseName,lblpassWord,lblh1,lblLoaiDN;
	private JTextField txtuserName;
	private JPasswordField txtpassWord;
	private JButton btnlogOn,btnExit;
	private JComboBox<String> cmbLoaidb;
	private DangNhapDaos dangNhapDaos;
	public Gui_DangNhap (){
		dangNhapDaos = new DangNhapDaos();
		setTitle("Phần Mềm Đăng Nhập");
		setSize(600,350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel pNorth;
		add(pNorth = new JPanel(),BorderLayout.NORTH);
		pNorth.setBorder(BorderFactory.createLineBorder(Color.red));
		pNorth.add(lbllogOn =new JLabel("Đăng nhập"));
		lbllogOn.setBackground(Color.red);
		lbllogOn.setFont(new Font("Times New Roman", Font.BOLD, 40));
		JPanel pTrai;
		add(pTrai = new JPanel(),BorderLayout.WEST);
		pTrai.add(lblh1 = new JLabel(new ImageIcon(".\\ima\\lock.png")));
		pTrai.setBorder(BorderFactory.createLineBorder(Color.red));
		Box bp =Box.createVerticalBox();
		add(bp,BorderLayout.CENTER);
		bp.add(Box.createVerticalStrut(50));
		bp.setBorder(BorderFactory.createLineBorder(Color.red));
		Box b1= Box.createHorizontalBox();
		bp.add(b1);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(lbluseName = new JLabel("User Name"));
		b1.add(Box.createHorizontalStrut(25));
		b1.add(txtuserName = new JTextField());
		b1.add(Box.createHorizontalStrut(50));
		Box b2= Box.createHorizontalBox();
		bp.add(b2);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(lblpassWord = new JLabel("PassWord"));
		b2.add(Box.createHorizontalStrut(30));
		b2.add(txtpassWord = new JPasswordField());
		b2.add(Box.createHorizontalStrut(50));

		
		Box b3 = Box.createHorizontalBox();
		String s[] = {"Nhân Viên Nhận Bệnh","Bác Sỹ","Nhân Viên Phát Thuốc","Quản Lý"};
		cmbLoaidb = new JComboBox<>(s);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(lblLoaiDN = new JLabel("Loại tài khoản"));
		b3.add(Box.createHorizontalStrut(10));
		b3.add(cmbLoaidb);
		b3.add(Box.createHorizontalStrut(20));
		bp.add(b3);
		bp.add(Box.createVerticalStrut(100));
		
		Box bd = Box.createHorizontalBox();
		add(bd,BorderLayout.SOUTH);
		bd.add(Box.createHorizontalStrut(100));
		bd.setBorder(BorderFactory.createLineBorder(Color.red));
		bd.add(btnlogOn = new JButton("Login",new ImageIcon(".\\ima\\login.png")));
		btnlogOn.setMaximumSize(getPreferredSize());
		bd.add(Box.createHorizontalStrut(10));
		bd.add(btnExit = new JButton("Exit",new ImageIcon(".\\ima\\logout.png")));
		btnExit.setMaximumSize(getPreferredSize());
		bd.add(Box.createHorizontalStrut(100));
		setVisible(true);
		setResizable(false);
		//////////////////////////////////////////////
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		btnlogOn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DangNhapAction();
				
			}
		});
	}
	private void DangNhapAction() {
		String username,password="";
		username = txtuserName.getText();
		char a[] = txtpassWord.getPassword();
		for (int i = 0; i < a.length; i++) {
			password = password + a[i];
		}
		String loai = cmbLoaidb.getItemAt(cmbLoaidb.getSelectedIndex());
		if(loai.equalsIgnoreCase("Nhân Viên Nhận Bệnh"))
		{
			NhanVien nhanVien = dangNhapDaos.LoginNhanVien(username, password);
			if(nhanVien != null && nhanVien.getTaiKhoan().getLoai().equalsIgnoreCase("Nhân Viên Nhận Bệnh")) {
				new Gui_NhanVienNhanBenh(nhanVien);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(),"Đăng nhập không thành công");
			}
				
		}else if(loai.equalsIgnoreCase("Nhân Viên Phát Thuốc"))
		{
			NhanVien nhanVien = dangNhapDaos.LoginNhanVien(username, password);
			if(nhanVien != null && nhanVien.getTaiKhoan().getLoai().equalsIgnoreCase("Nhân Viên Phát Thuốc")) {
				new Gui_NhanVienPhatThuoc(nhanVien);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(),"Đăng nhập không thành công");
			}
		}
		else if(loai.equalsIgnoreCase("Bác Sỹ"))
		{
			NhanVien nhanVien = dangNhapDaos.LoginNhanVien(username, password);
			if(nhanVien != null && nhanVien.getTaiKhoan().getLoai().equalsIgnoreCase("Bác Sỹ")) {
				new Gui_BacSi(nhanVien);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(),"Đăng nhập không thành công");
			}
		}
		if(loai.equalsIgnoreCase("Quản Lý"))
		{
			NhanVien nhanVien = dangNhapDaos.LoginNhanVien(username, password);
			if(nhanVien != null && nhanVien.getTaiKhoan().getLoai().equalsIgnoreCase("Quản Lý")) {
				new Gui_QuanLy(nhanVien);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(),"Đăng nhập không thành công");
			}
		}
		
	}
}