package gui;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.jidesoft.swing.ComboBoxSearchable;

import daos.NhanVienNhanBenhDaos;
import htqlbv_entities.BenhNhan;
import htqlbv_entities.NhanVien;


public class Gui_NhanVienNhanBenh extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	///////////////
	private NhanVien nhanVien;
	///////////////
	private JPasswordField txtmkc,txtmkm,txtnlmkm;
	private JTabbedPane tbpqlcn;
	private JLabel lblmanv,lbltennv,lblhonv,lblsdtnv,lbldiacchinv,lblngaysinhnv,lblmkc,lblmkm,lblnlmkm,lblTimbs;
	private JTextField txtmanv,txthonv,txttennv,txtsdtnv,txtdiachinv,txtngaysinhnv;
	private JButton btnDoimk,btnHuy;
	///////////////
	private JTabbedPane tbp;
	private JButton btnDangXuat,btnTimbn,btnThem,btnLuu,btnCapNhat,btnChuyen,btnTimbs;
	private JLabel lblAnh,lblidbn,lblhobn,lbltenbn,lbldiachibn,lblsdtbn;
	private JTextField txtidbn,txthobn,txttenbn,txtdiachibn,txtsdtbn,txttimbn,txttimbs;
	private JComboBox<String> cmbTim,cmbTimbs;
	private JTable table;
	private DefaultTableModel tablemodel;
	private NhanVienNhanBenhDaos nhanVienNhanBenhDaos;
	private List<BenhNhan> dsbn;
	private List<NhanVien> dsnv;
	public Gui_NhanVienNhanBenh(NhanVien nhanVien)
	{
		this.nhanVien = nhanVien;
		nhanVienNhanBenhDaos = new NhanVienNhanBenhDaos();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900,600);
		setResizable(false);
		setTitle("Nhân Viên Nhận Bệnh");
		Box bt = Box.createVerticalBox();//Cái này là quản lý chung của cả frame
		/**
		 * Cái này là tiêu đề
		 */
		////////////////////////////////
		Box b1 = Box.createHorizontalBox();
		b1.add(lblAnh = new JLabel(new ImageIcon("D:\\Luc\\Dai Hoc\\Mon Hoc\\Lap trinh huong su kien\\BaiTapLonJaVa\\plus.png")));
		b1.add(btnDangXuat = new JButton("Đăng Xuất"));
		bt.add(Box.createVerticalStrut(10));
		bt.add(b1);
		bt.add(Box.createVerticalStrut(10));
		/////////////////////////////////
		/**
		 * Cái này là quản lý bệnh nhân
		 */
		/////////////////////////////////
		Box bqlbn = Box.createVerticalBox(); // bqlbn là box chung quản lý toàn bộ quản lý bệnh nhân
		
		Box bqlbn1 = Box.createHorizontalBox(); // bqlbn1 là box để quản lý dòng trên cùng 
		Box bqlbn1_ThongTin = Box.createVerticalBox();
		bqlbn1_ThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin bệnh nhân"));	
		Box bqlbn1_ThongTin_IdBN = Box.createHorizontalBox();
		bqlbn1_ThongTin_IdBN.add(lblidbn = new JLabel("Mã số bệnh nhân: "));
		bqlbn1_ThongTin_IdBN.add(Box.createHorizontalStrut(20));// Khoảng cách giữa chữ và textfield
		bqlbn1_ThongTin_IdBN.add(txtidbn = new JTextField());
		txtidbn.setPreferredSize(getPreferredSize());
		Box bqlbn1_ThongTin_Ho = Box.createHorizontalBox();
		bqlbn1_ThongTin_Ho.add(lblhobn = new JLabel("Họ: "));
		bqlbn1_ThongTin_Ho.add(Box.createHorizontalStrut(100));
		bqlbn1_ThongTin_Ho.add(txthobn = new JTextField());
		txthobn.setPreferredSize(getPreferredSize());
		Box bqlbn1_ThongTin_Ten = Box.createHorizontalBox();
		bqlbn1_ThongTin_Ten.add(lbltenbn = new JLabel("Tên: "));
		bqlbn1_ThongTin_Ten.add(Box.createHorizontalStrut(95));
		bqlbn1_ThongTin_Ten.add(txttenbn = new JTextField());
		txttenbn.setPreferredSize(getPreferredSize());
		Box bqlbn1_ThongTin_Sdt = Box.createHorizontalBox();
		bqlbn1_ThongTin_Sdt.add(lblsdtbn = new JLabel("Số điện thoại: "));
		bqlbn1_ThongTin_Sdt.add(Box.createHorizontalStrut(45));
		bqlbn1_ThongTin_Sdt.add(txtsdtbn = new JTextField());
		txtsdtbn.setPreferredSize(getPreferredSize());
		Box bqlbn1_ThongTin_DiaChi = Box.createHorizontalBox();
		bqlbn1_ThongTin_DiaChi.add(lbldiachibn = new JLabel("Địa chỉ: "));
		bqlbn1_ThongTin_DiaChi.add(Box.createHorizontalStrut(80));
		bqlbn1_ThongTin_DiaChi.add(txtdiachibn = new JTextField());
		txtdiachibn.setPreferredSize(getPreferredSize());
		
		bqlbn1_ThongTin.add(bqlbn1_ThongTin_IdBN);
		bqlbn1_ThongTin.add(Box.createVerticalStrut(5));
		bqlbn1_ThongTin.add(bqlbn1_ThongTin_Ho);
		bqlbn1_ThongTin.add(Box.createVerticalStrut(5));
		bqlbn1_ThongTin.add(bqlbn1_ThongTin_Ten);
		bqlbn1_ThongTin.add(Box.createVerticalStrut(5));
		bqlbn1_ThongTin.add(bqlbn1_ThongTin_Sdt);
		bqlbn1_ThongTin.add(Box.createVerticalStrut(5));
		bqlbn1_ThongTin.add(bqlbn1_ThongTin_DiaChi);
		
		Box bqlbn1_TimKiem_BenhNhan_BacSi = Box.createVerticalBox();
		Box bqlbn1_TimKiem = Box.createVerticalBox();
		bqlbn1_TimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm bệnh nhân: "));
		Box bqlbn1_TimKiem_1 = Box.createHorizontalBox();
		String [] chon = {"Theo tên","Theo số điện thoại"};
		bqlbn1_TimKiem_1.add(Box.createHorizontalStrut(5));
		bqlbn1_TimKiem_1.add(cmbTim = new JComboBox<String>(chon));
		bqlbn1_TimKiem_1.add(Box.createHorizontalStrut(5));
		bqlbn1_TimKiem_1.add(txttimbn = new JTextField(5));
		bqlbn1_TimKiem_1.add(Box.createHorizontalStrut(5));// Khoảng cách giữa các phần tử trong box tìm kiếm
		bqlbn1_TimKiem_1.add(btnTimbn = new JButton("Tim"));
		bqlbn1_TimKiem.add(bqlbn1_TimKiem_1);
		bqlbn1_TimKiem.add(Box.createVerticalStrut(20));
		bqlbn1_TimKiem.setMinimumSize(getMinimumSize());
		bqlbn1_TimKiem_BenhNhan_BacSi.add(bqlbn1_TimKiem);
		
		Box bqlbn1_TimKiem_BS = Box.createVerticalBox();
		bqlbn1_TimKiem_BS.setBorder(BorderFactory.createTitledBorder("Tìm kiếm bác sỹ"));
		Box bqlbn1_TimKiem_BS_1 = Box.createHorizontalBox();
		bqlbn1_TimKiem_BS_1.add(lblTimbs = new JLabel("Tên Bác Sỹ"));
		bqlbn1_TimKiem_BS_1.add(Box.createHorizontalStrut(20));
		bqlbn1_TimKiem_BS_1.add(cmbTimbs = new JComboBox<String>());
		bqlbn1_TimKiem_BS_1.add(Box.createHorizontalStrut(20));
		
		bqlbn1_TimKiem_BS.add(bqlbn1_TimKiem_BS_1);
		bqlbn1_TimKiem_BS.add(Box.createHorizontalStrut(20));
		bqlbn1_TimKiem_BS.setMaximumSize(getMaximumSize());
		bqlbn1_TimKiem_BenhNhan_BacSi.add(bqlbn1_TimKiem_BS);
		
		
		bqlbn1.add(bqlbn1_ThongTin);
		bqlbn1.add(Box.createHorizontalStrut(10));// Giản cách giữa hai box thông tin và tìm kiếm
		bqlbn1.add(bqlbn1_TimKiem_BenhNhan_BacSi);
		
		Box bqlbn2_ChucNang = Box.createVerticalBox(); // bqlbn2_ChucNang là quản lý dòng các nút chức năng
		bqlbn2_ChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		bqlbn2_ChucNang.setMaximumSize(getMaximumSize());
		Box bqlbn2_ChucNang_1 = Box.createHorizontalBox();
		bqlbn2_ChucNang_1.add(Box.createHorizontalStrut(10));
		bqlbn2_ChucNang_1.add(btnThem = new JButton("Thêm",new ImageIcon("plus.png")));
		btnThem.setMaximumSize(getMaximumSize());
		bqlbn2_ChucNang_1.add(Box.createHorizontalStrut(10));
		bqlbn2_ChucNang_1.add(btnCapNhat = new JButton("Cập Nhật",new ImageIcon("available_updates.png")));
		btnCapNhat.setMaximumSize(getMaximumSize());
		bqlbn2_ChucNang_1.add(Box.createHorizontalStrut(10));
		bqlbn2_ChucNang_1.add(btnLuu = new JButton("Lưu",new ImageIcon("save.png")));
		btnLuu.setMaximumSize(getMaximumSize());
		bqlbn2_ChucNang_1.add(Box.createHorizontalStrut(10));
		bqlbn2_ChucNang_1.add(btnChuyen = new JButton("Chuyển bệnh nhân",new ImageIcon("plus.png")));
		btnChuyen.setMaximumSize(getMaximumSize());
		bqlbn2_ChucNang_1.add(Box.createHorizontalStrut(10));

		bqlbn2_ChucNang.add(bqlbn2_ChucNang_1);
		
		Box bqlbn3_Danhsach = Box.createVerticalBox(); // bqlbn3_Danhsach là quản lý cái bảng danh sách
		bqlbn3_Danhsach.setBorder(BorderFactory.createTitledBorder("Danh sách bệnh nhân"));
		Box bqlbn3_Danhsach_1 = Box.createHorizontalBox();
		String [] header = {"Mã số bệnh nhân","Họ","Tên","Số điện thoại","Địa chỉ"}; 
		tablemodel = new DefaultTableModel(header, 0);
		JScrollPane scroll;
		bqlbn3_Danhsach_1.add(scroll = new JScrollPane(table = new JTable(tablemodel)));
		table.setDefaultEditor(Object.class, null);
		bqlbn3_Danhsach.add(bqlbn3_Danhsach_1);
		
		bqlbn.add(bqlbn1);
		bqlbn.add(bqlbn2_ChucNang);
		bqlbn.add(bqlbn3_Danhsach);
		/////////////////////////////////
		/**
		 * Cái này là quản lý cá nhân
		 */
		/////////////////////////////////
		tbpqlcn = new JTabbedPane();
		Box bqlcn_XemThongTin,bqlcn_DoiMatKhau;
		bqlcn_XemThongTin = Box.createVerticalBox();//bqlcn_XemThongTin quản lý chung của xem thông tin
		Box bqlcn_XemThongTin_Manv = Box.createHorizontalBox();
		bqlcn_XemThongTin_Manv.add(Box.createHorizontalStrut(10));
		bqlcn_XemThongTin_Manv.add(lblmanv = new JLabel("Mã số nhân viên:"));
		bqlcn_XemThongTin_Manv.add(Box.createHorizontalStrut(100));
		bqlcn_XemThongTin_Manv.add(txtmanv = new JTextField());
		bqlcn_XemThongTin_Manv.add(Box.createHorizontalStrut(50));
		Box bqlcn_XemThongTin_Ho = Box.createHorizontalBox();
		bqlcn_XemThongTin_Ho.add(Box.createHorizontalStrut(10));
		bqlcn_XemThongTin_Ho.add(lblhonv = new JLabel("Họ:"));
		bqlcn_XemThongTin_Ho.add(Box.createHorizontalStrut(170));
		bqlcn_XemThongTin_Ho.add(txthonv = new JTextField());
		bqlcn_XemThongTin_Ho.add(Box.createHorizontalStrut(50));
		Box bqlcn_XemThongTin_Ten = Box.createHorizontalBox();
		bqlcn_XemThongTin_Ten.add(Box.createHorizontalStrut(10));
		bqlcn_XemThongTin_Ten.add(lbltennv = new JLabel("Tên:"));
		bqlcn_XemThongTin_Ten.add(Box.createHorizontalStrut(165));
		bqlcn_XemThongTin_Ten.add(txttennv = new JTextField());
		bqlcn_XemThongTin_Ten.add(Box.createHorizontalStrut(50));
		Box bqlcn_XemThongTin_Sdt = Box.createHorizontalBox();
		bqlcn_XemThongTin_Sdt.add(Box.createHorizontalStrut(10));
		bqlcn_XemThongTin_Sdt.add(lblsdtnv = new JLabel("Số điện thoại:"));
		bqlcn_XemThongTin_Sdt.add(Box.createHorizontalStrut(110));
		bqlcn_XemThongTin_Sdt.add(txtsdtnv = new JTextField());
		bqlcn_XemThongTin_Sdt.add(Box.createHorizontalStrut(50));
		Box bqlcn_XemThongTin_NgaySinh = Box.createHorizontalBox();
		bqlcn_XemThongTin_NgaySinh.add(Box.createHorizontalStrut(10));
		bqlcn_XemThongTin_NgaySinh.add(lblngaysinhnv = new JLabel("Ngày sinh:"));
		bqlcn_XemThongTin_NgaySinh.add(Box.createHorizontalStrut(120));
		bqlcn_XemThongTin_NgaySinh.add(txtngaysinhnv = new JTextField());
		bqlcn_XemThongTin_NgaySinh.add(Box.createHorizontalStrut(50));
		Box bqlcn_XemThongTin_Diachi = Box.createHorizontalBox();
		bqlcn_XemThongTin_Diachi.add(Box.createHorizontalStrut(10));
		bqlcn_XemThongTin_Diachi.add(lbldiacchinv = new JLabel("Địa chỉ:"));
		bqlcn_XemThongTin_Diachi.add(Box.createHorizontalStrut(140));
		bqlcn_XemThongTin_Diachi.add(txtdiachinv = new JTextField());
		bqlcn_XemThongTin_Diachi.add(Box.createHorizontalStrut(50));
		
		bqlcn_XemThongTin.add(Box.createVerticalStrut(30));
		bqlcn_XemThongTin.add(bqlcn_XemThongTin_Manv);
		bqlcn_XemThongTin.add(Box.createVerticalStrut(30));
		bqlcn_XemThongTin.add(bqlcn_XemThongTin_Ho);
		bqlcn_XemThongTin.add(Box.createVerticalStrut(30));
		bqlcn_XemThongTin.add(bqlcn_XemThongTin_Ten);
		bqlcn_XemThongTin.add(Box.createVerticalStrut(30));
		bqlcn_XemThongTin.add(bqlcn_XemThongTin_Sdt);
		bqlcn_XemThongTin.add(Box.createVerticalStrut(30));
		bqlcn_XemThongTin.add(bqlcn_XemThongTin_NgaySinh);
		bqlcn_XemThongTin.add(Box.createVerticalStrut(30));
		bqlcn_XemThongTin.add(bqlcn_XemThongTin_Diachi);
		bqlcn_XemThongTin.add(Box.createVerticalStrut(100));
		
		bqlcn_DoiMatKhau = Box.createVerticalBox();//bqlcn_DoiMatKhau là quản lý chung của đổi mật khẩu
		Box bqlcn_DoiMatKhau_mkc = Box.createHorizontalBox();
		bqlcn_DoiMatKhau_mkc.add(Box.createHorizontalStrut(20));
		bqlcn_DoiMatKhau_mkc.add(lblmkc = new JLabel("Nhập lại mật khẩu cũ"));
		bqlcn_DoiMatKhau_mkc.add(Box.createHorizontalStrut(50));
		bqlcn_DoiMatKhau_mkc.add(txtmkc = new JPasswordField());
		bqlcn_DoiMatKhau_mkc.add(Box.createHorizontalStrut(20));
		Box bqlcn_DoiMatKhau_mkm = Box.createHorizontalBox();
		bqlcn_DoiMatKhau_mkm.add(Box.createHorizontalStrut(20));
		bqlcn_DoiMatKhau_mkm.add(lblmkm = new JLabel("Nhập mật khẩu mới"));
		bqlcn_DoiMatKhau_mkm.add(Box.createHorizontalStrut(55));
		bqlcn_DoiMatKhau_mkm.add(txtmkm = new JPasswordField());
		bqlcn_DoiMatKhau_mkm.add(Box.createHorizontalStrut(20));
		Box bqlcn_DoiMatKhau_nlmkm = Box.createHorizontalBox();
		bqlcn_DoiMatKhau_nlmkm.add(Box.createHorizontalStrut(20));
		bqlcn_DoiMatKhau_nlmkm.add(lblnlmkm = new JLabel("Nhập lại mật khẩu mới"));
		bqlcn_DoiMatKhau_nlmkm.add(Box.createHorizontalStrut(40));
		bqlcn_DoiMatKhau_nlmkm.add(txtnlmkm = new JPasswordField());
		bqlcn_DoiMatKhau_nlmkm.add(Box.createHorizontalStrut(20));
		Box bqlcn_DoiMatKhau_button = Box.createHorizontalBox();
		bqlcn_DoiMatKhau_button.add(Box.createHorizontalStrut(20));
		bqlcn_DoiMatKhau_button.add(btnDoimk = new JButton("Đổi mật khẩu"));
		bqlcn_DoiMatKhau_button.add(Box.createHorizontalStrut(50));
		bqlcn_DoiMatKhau_button.add(btnHuy = new JButton("Hủy"));
		bqlcn_DoiMatKhau_button.add(Box.createHorizontalStrut(20));
		bqlcn_DoiMatKhau.add(Box.createVerticalStrut(10));
		bqlcn_DoiMatKhau.add(bqlcn_DoiMatKhau_mkc);
		bqlcn_DoiMatKhau.add(Box.createVerticalStrut(50));
		bqlcn_DoiMatKhau.add(bqlcn_DoiMatKhau_mkm);
		bqlcn_DoiMatKhau.add(Box.createVerticalStrut(50));
		bqlcn_DoiMatKhau.add(bqlcn_DoiMatKhau_nlmkm);
		bqlcn_DoiMatKhau.add(Box.createVerticalStrut(50));
		bqlcn_DoiMatKhau.add(bqlcn_DoiMatKhau_button);
		bqlcn_DoiMatKhau.add(Box.createVerticalStrut(200));
		tbpqlcn.addTab("Xem Thông tin",bqlcn_XemThongTin);
		tbpqlcn.addTab("Đổi mật khẩu", bqlcn_DoiMatKhau);
		/////////////////////////////////
		/**
		 * Cái này là quản lý các tab
		 */
		/////////////////////////////////
		tbp = new JTabbedPane();
		tbp.addTab("Quản lý cá nhân", tbpqlcn);
		tbp.addTab("Quản lý bệnh nhân", bqlbn);
		tbp.setTabPlacement(JTabbedPane.LEFT);
	    /////////////////////////////////
		/**
		 * Xử lý trạng thái ban đầu
		 */
		/////////////////////////////////
		txtmanv.setEditable(false);
		txttennv.setEditable(false);
		txthonv.setEditable(false);
		txtngaysinhnv.setEditable(false);
		txtdiachinv.setEditable(false);
		txtsdtnv.setEditable(false);
		
		txtidbn.setEditable(false);
		txttenbn.setEditable(false);
		txthobn.setEditable(false);
		txtsdtbn.setEditable(false);
		txtdiachibn.setEditable(false);
		
		btnLuu.setEnabled(false);
		table.setDefaultEditor(Object.class, null);
		table.setEnabled(false);
		
		cmbTim.setEnabled(false);
		txttimbn.setEnabled(false);
		btnTimbn.setEnabled(false);
		
		cmbTimbs.setEnabled(false);
		
		/////////////////////////////////
	    bt.add(tbp);
	    add(bt);
		setLocationRelativeTo(null);
		setVisible(true);
		/////////////////////////////////
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ThemAction();
				
			}
		});
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LuuAction();
				System.out.println("a");
				
			}
		});
		btnCapNhat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CapNhatAction();
				
			}
		});
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(table.isEnabled())
					ChonDongTrongBangBenhNhan();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnChuyen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ChuyenBenhNhan();
				
			}
		});
		btnTimbn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TimBenhNhan();
				
			}
		});
		btnDangXuat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int chon = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn đăng xuất","Warning",JOptionPane.YES_NO_OPTION);
				if(chon == 0)
				{
					new Gui_DangNhap();
					dispose();
				}
				
			}
		});
		btnHuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				XoaTrangDoiMatKhau();
			}
		});
		btnDoimk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DoiMatKhau();
				
			}
		});
		/////////////////////////////////
		LoadBenhNhanVaoBang();
		LoadTenBacSi();
		HienThiThongTinCaNhan();
	}
	/**
	 * các hàm xử lý
	 */
////////////////////////////////////////////////////////////////////////////////////
	private void XoaTrangDoiMatKhau()
	{
		txtmkc.setText("");
		txtmkm.setText("");
		txtnlmkm.setText("");
	}
	private void LoadTenBacSi()
	{
		dsnv = new ArrayList<>();
		dsnv = nhanVienNhanBenhDaos.GetBacSy();
		for (int i = 0; i < dsnv.size(); i++) {
			String s = dsnv.get(i).getHo() + " " + dsnv.get(i).getTen();
			cmbTimbs.addItem(s);
		}
		
	}
	private void LoadBenhNhanVaoBang ()
	{
		dsbn = new ArrayList<>();
		dsbn = nhanVienNhanBenhDaos.LayBenhNhanTuSQL();
		for (int i = 0; i < dsbn.size(); i++) {
			String s[] = {dsbn.get(i).getIDBenhNhan(),dsbn.get(i).getHo(),dsbn.get(i).getTenBN(),dsbn.get(i).getSDT(),dsbn.get(i).getDiaChi()};
			tablemodel.addRow(s);
		}
	}
	private void XoaTrangBenhNhan()
	{
		txtidbn.setText("");
		txttenbn.setText("");
		txthobn.setText("");
		txtsdtbn.setText("");
		txtdiachibn.setText("");
		
	}
	private void XoaBangBenhNhan() {
		int a = tablemodel.getRowCount();
		for (int i = a-1; i >= 0; i--) {
			tablemodel.removeRow(i);
		}
	}
	private void ChonDongTrongBangBenhNhan() {
		int a = table.getSelectedRow();
		txtidbn.setText(table.getValueAt(a, 0).toString());
		txthobn.setText(table.getValueAt(a, 1).toString());
		txttenbn.setText(table.getValueAt(a, 2).toString());
		txtsdtbn.setText(table.getValueAt(a, 3).toString());
		txtdiachibn.setText(table.getValueAt(a, 4).toString());
	}
	private void TrangThaiTextBenhNhan(boolean a)
	{
		txtidbn.setEditable(a);
		txttenbn.setEditable(a);
		txthobn.setEditable(a);
		txtsdtbn.setEditable(a);
		txtdiachibn.setEditable(a);
	}
	private void HienThiThongTinCaNhan()
	{
		txtmanv.setText(nhanVien.getIDNhanVien());
		txttennv.setText(nhanVien.getTen());
		txthonv.setText(nhanVien.getHo());
		txtdiachinv.setText(nhanVien.getDiaChi());
		txtsdtnv.setText(nhanVien.getSDT());
		txtngaysinhnv.setText(nhanVien.getNgaySinh().toString());
	}
	////////////////////////////////////////////////////////////////////////////////////////
	private void ThemAction()
	{
		if(btnThem.getText().equalsIgnoreCase("Thêm")) {
			TrangThaiTextBenhNhan(true);
			btnThem.setText("Hủy");
			btnCapNhat.setEnabled(false);
			btnChuyen.setEnabled(false);
			btnLuu.setEnabled(true);
			txtidbn.requestFocus();
		}else if(btnThem.getText().equalsIgnoreCase("Hủy")) {
			TrangThaiTextBenhNhan(false);
			btnThem.setText("Thêm");
			btnCapNhat.setEnabled(true);
			btnChuyen.setEnabled(true);
			btnLuu.setEnabled(false);

		}
	}
	private void LuuAction()
	{
		if(btnThem.getText().equalsIgnoreCase("Hủy")) {
			BenhNhan benhNhan = new BenhNhan();
			benhNhan.setIDBenhNhan(txtidbn.getText());
			benhNhan.setHo(txthobn.getText());
			benhNhan.setTenBN(txttenbn.getText());
			benhNhan.setDiaChi(txtdiachibn.getText());
			benhNhan.setSDT(txtsdtbn.getText());
			nhanVienNhanBenhDaos.ThemBenhNhanVaoDataBase(benhNhan);
			XoaBangBenhNhan();
			LoadBenhNhanVaoBang();
			XoaTrangBenhNhan();
		}else if(btnCapNhat.getText().equals("Hủy")) {
			if(table.getSelectedRow()>=0) {
				BenhNhan benhNhan = new BenhNhan();
				benhNhan.setIDBenhNhan(txtidbn.getText());
				benhNhan.setHo(txthobn.getText());
				benhNhan.setTenBN(txttenbn.getText());
				benhNhan.setDiaChi(txtdiachibn.getText());
				benhNhan.setSDT(txtsdtbn.getText());
				nhanVienNhanBenhDaos.SuaBenhNhanVaoDatabase(benhNhan);
				XoaBangBenhNhan();
				LoadBenhNhanVaoBang();
				XoaTrangBenhNhan();
			}else {
				JOptionPane.showMessageDialog(new JFrame(),"Chưa chọn bệnh nhân để cập nhật");
				XoaTrangBenhNhan();
			}		
		} else if(btnChuyen.getText().equals("Hủy"))
		{
			if(table.getSelectedRow()>=0) {
				BenhNhan benhNhan = new BenhNhan();
				benhNhan.setIDBenhNhan(txtidbn.getText());
				benhNhan.setHo(txthobn.getText());
				benhNhan.setTenBN(txttenbn.getText());
				benhNhan.setDiaChi(txtdiachibn.getText());
				benhNhan.setSDT(txtsdtbn.getText());
				NhanVien nhanVien = new NhanVien();
				nhanVien = dsnv.get(cmbTimbs.getSelectedIndex());
				nhanVienNhanBenhDaos.ChuyenBenhNhanQuaBacSy(nhanVien, benhNhan);
				XoaBangBenhNhan();
				XoaTrangBenhNhan();
			}else {
				JOptionPane.showMessageDialog(new JFrame(),"Chưa chọn bệnh nhân để cập nhật");
				txttimbn.setText("");
			}

		}

	}
	private void CapNhatAction() {
		if(btnCapNhat.getText().equals("Cập Nhật"))
		{
			TrangThaiTextBenhNhan(true);
			btnCapNhat.setText("Hủy");
			btnThem.setEnabled(false);
			btnChuyen.setEnabled(false);
			btnLuu.setEnabled(true);
			table.setEnabled(true);
			txttimbn.setEnabled(true);
			btnTimbn.setEnabled(true);
			cmbTim.setEnabled(true);
			XoaTrangBenhNhan();
			XoaBangBenhNhan();
		}else if(btnCapNhat.getText().equals("Hủy"))
		{
			TrangThaiTextBenhNhan(false);
			btnCapNhat.setText("Cập Nhật");
			btnThem.setEnabled(true);
			btnChuyen.setEnabled(true);
			btnLuu.setEnabled(false);
			table.getSelectionModel().clearSelection();
			table.setEnabled(false);
			txttimbn.setEnabled(false);
			btnTimbn.setEnabled(false);
			cmbTim.setEnabled(false);
			XoaTrangBenhNhan();
			XoaBangBenhNhan();
			txttimbn.setText("");
			LoadBenhNhanVaoBang();
		}
	}
	public void ChuyenBenhNhan() {
		if(btnChuyen.getText().equals("Chuyển bệnh nhân"))
		{
			btnChuyen.setText("Hủy");
			btnLuu.setText("Chuyển");
			btnThem.setEnabled(false);
			btnCapNhat.setEnabled(false);
			cmbTimbs.setEnabled(true);
			btnLuu.setEnabled(true);
			txttimbn.setEnabled(true);
			btnTimbn.setEnabled(true);
			cmbTim.setEnabled(true);
			table.setEnabled(true);
		}else if(btnChuyen.getText().equals("Hủy")) {
			XoaBangBenhNhan();
			txttimbn.setText("");
			btnChuyen.setText("Chuyển bệnh nhân");
			btnLuu.setText("Lưu");
			btnThem.setEnabled(true);
			btnCapNhat.setEnabled(true);
			cmbTimbs.setEnabled(false);
			btnLuu.setEnabled(false);
			txttimbn.setEnabled(false);
			btnTimbn.setEnabled(false);
			cmbTim.setEnabled(false);
			table.getSelectionModel().clearSelection();
			table.setEnabled(false);
			LoadBenhNhanVaoBang();
		}
	}
	public void TimBenhNhan()
	{
		if(cmbTim.getSelectedIndex()==0 && !txttimbn.getText().isEmpty())
		{
			XoaBangBenhNhan();
			dsbn = new ArrayList<>();
			dsbn = nhanVienNhanBenhDaos.TimBenhNhanTheoTen(txttimbn.getText());
			if(dsbn !=null) {
				for (int i = 0; i < dsbn.size(); i++) {
					String s[] = {dsbn.get(i).getIDBenhNhan(),dsbn.get(i).getHo(),dsbn.get(i).getTenBN(),dsbn.get(i).getSDT(),dsbn.get(i).getDiaChi()};
					tablemodel.addRow(s);
				}
				txttimbn.setText("");
			}
			else
				JOptionPane.showMessageDialog(new JFrame(),"Không tìm thấy");
		}
		else if(cmbTim.getSelectedIndex()==1 && !txttimbn.getText().isEmpty()) 
		{
			XoaBangBenhNhan();
			dsbn = new ArrayList<>();
			dsbn = nhanVienNhanBenhDaos.TimBenhNhanTheoSDT(txttimbn.getText());
			if(!dsbn.isEmpty())
			{
				for (int i = 0; i < dsbn.size(); i++) {
					String s[] = {dsbn.get(i).getIDBenhNhan(),dsbn.get(i).getHo(),dsbn.get(i).getTenBN(),dsbn.get(i).getSDT(),dsbn.get(i).getDiaChi()};
					tablemodel.addRow(s);
				}
				txttimbn.setText("");
			}
			else
				JOptionPane.showMessageDialog(new JFrame(),"Không tìm thấy");

		}
	}
	/////////////////////////////////////////////////////////////////////////
	private boolean DoiMatKhau()
	{
		String mkc= "",mkm = "",nlmkm = "";
		char a[],b[],c[];
		a = txtmkc.getPassword();
		b = txtmkm.getPassword();
		c = txtnlmkm.getPassword();
		for (int i = 0; i < a.length; i++) {
			mkc = mkc + a[i];
		}
		for (int i = 0; i < b.length; i++) {
			mkm = mkm + b[i];
		}
		for (int i = 0; i < c.length; i++) {
			nlmkm = nlmkm + c[i];
		}
		if(!nhanVienNhanBenhDaos.CheckMatKhauCu(nhanVien, mkc)) {
			JOptionPane.showMessageDialog(new JFrame(),"Mật khẩu cũ không khớp");
			XoaTrangDoiMatKhau();
			return false;
		}else if(!mkm.equals(nlmkm)) {
			JOptionPane.showMessageDialog(new JFrame(),"Nhập lại mật khẩu mới không khớp");
			XoaTrangDoiMatKhau();
			return false;
		}else if(!nhanVienNhanBenhDaos.DoiMatKhau(nhanVien, nlmkm)) {
			JOptionPane.showMessageDialog(new JFrame(),"Đổi mật khẩu không thành công");
			XoaTrangDoiMatKhau();
			return false;
		}
		JOptionPane.showMessageDialog(new JFrame(),"Đổi mật khẩu thành công");
		new Gui_DangNhap();
		dispose();
		return true;
	}
}
