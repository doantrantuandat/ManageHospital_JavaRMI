package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import daos.BenhAnDaos;
import daos.BenhNhanDaos;
import daos.ChiTietKhoThuocDaos;
import daos.NhanVienDaos;
import daos.QuanLyDaos;
import daos.TaiKhoanDaos;
import daos.ThuocDaos;
import daos.ToaThuocDaos;
import htqlbv_entities.BenhAn;
import htqlbv_entities.BenhNhan;
import htqlbv_entities.ChiTietKhoThuoc;
import htqlbv_entities.NhanVien;
import htqlbv_entities.TaiKhoan;
import htqlbv_entities.Thuoc;
import htqlbv_entities.ToaThuoc;

public class Gui_QuanLy extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnDong;

	private QuanLyDaos quanLyDaos;

	private List<BenhNhan> dsBenhNhan;
	private List<TaiKhoan> dsTaiKhoan;
	private List<Thuoc> dsThuoc;
	private List<ChiTietKhoThuoc> dsChiTietToaThuoc;
	private List<ToaThuoc> dsToaThuoc;
	private List<NhanVien> dsNhanVien;

	// Khai bao cho tab quan ly benh nhan
	private JLabel lblBenhNhanID, lblHoBenhNhan, lblTenBenhNhan, lblSDTBenhNhan, lblDiaChiBenhNhan, lblTimKiemBenhNhan,
			lblXoaBenhNhan;
	private JTextField txtBenhNhanID, txtHoBenhNhan, txtTenBenhNhan, txtSDTBenhNhan, txtDiaChiBenhNhan,
			txtTimKiemBenhNhan;
	private JButton btnTimBenhNhan, btnThemBenhNhan, btnSuaBenhNhan, btnLuuBenhNhan, btnXoaBenhNhan;
	private JComboBox<String> cbbTimKiemBenhNhan;
	private JTable tableBenhNhan;
	private DefaultTableModel modelBenhNhan;

	// Khai bao cho tab quan ly nhan vien
	private JLabel lblNhanVienID, lblHoNhanVien, lblTenNhanVien, lblSDTNhanVien, lblNSNhanVien, lblDiaChiNhanVien;
	private JTextField txtNhanVienID, txtHoNhanVien, txtTenNhanVien, txtSDTNhanVien, txtNSNhanVien, txtDiaChiNhanVien,
			txtTimNhanVien;
	private DatePicker datePicker;
	private JButton btnThemNV, btnSuaNV, btnLuuNV, btnTimNV, btnXoaNV, btnCapNhat;
	private JComboBox<String> cbbTimNhanVien;
	private JTable tableNhanVien;
	private DefaultTableModel modelNhanVien;

	// Khai bao cho tab quan ly tai khoan
	private JLabel lblTenTaiKhoan, lblMatKhau, lblLoaiTaiKhoan, lblNVID;
	private JTextField txtTenTaiKhoan, txtMatKhau, txtNVID;
	private JButton btnThemTaiKhoan, btnSuaTaiKhoan, btnLuuTaiKhoan, btnXoaTaiKhoan;
	private JComboBox<String> cbbLoaiTaiKhoan;
	private JTable tableTaiKhoan;
	private DefaultTableModel modelTaiKhoan;

	// Khai bao cho tab quan ly toa thuoc
	private JLabel lblToaThuocID, lblDonGia, lblGhiChu, lblTrangThai;
	private JTextField txtToaThuocID, txtDonGia, txtGhiChu, txtTrangThai;
	private JTable tableToaThuoc, tableChiTietToa;
	private DefaultTableModel modelToaThuoc, modelChiTietToa;

	// Khai bao quan ly thuoc
	private JLabel lblThuocID, lblGiaThuoc, lblTenThuoc;
	private JTextField txtThuocID, txtGiaThuoc, txtTenThuoc;
	private JTable tableThuoc;
	private DefaultTableModel modelThuoc;

	// Khai bao quan ly ca nhan
	private JLabel lblCaNhanID, lblHoCaNhan, lblTenCaNhan, lblSDTCaNhan, lblNSCaNhan, lblDiaChiCaNhan, lblMatKhauCu,
			lblMatKhauMoi, lblNhapLaiMK;
	private JTextField txtCaNhanID, txtHoCaNhan, txtTenCaNhan, txtSDTCaNhan, txtNSCaNhan, txtDiaChiCaNhan;
	private JPasswordField pwfMatKhauCu, pwfMatKhauMoi, pwfNhapLaiMK;
	private JButton btnDoi, btnHuy;
	private NhanVien nhanVien;
	
	public Gui_QuanLy(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
		setSize(1100, 800);
		setTitle("Nhân Viên Quản Lý");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ------------------------------------------------------------------------------
		JPanel pNorth = new JPanel();
		pNorth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pNorth.add(btnDong = new JButton("Đăng xuất"));
		pNorth.add(Box.createHorizontalStrut(7));

		add(pNorth, BorderLayout.NORTH);

		quanLyDaos = new QuanLyDaos();

		dsBenhNhan = new ArrayList<BenhNhan>();
		dsTaiKhoan = new ArrayList<TaiKhoan>();
		dsThuoc = new ArrayList<Thuoc>();
		dsChiTietToaThuoc = new ArrayList<ChiTietKhoThuoc>();
		dsToaThuoc = new ArrayList<ToaThuoc>();
		dsNhanVien = new ArrayList<NhanVien>();

		// ------------------------------------------------------------------------------
		JTabbedPane tab = new JTabbedPane();
		tab.setTabPlacement(JTabbedPane.LEFT);

		/*
		 * Giao dien quan ly benh nhan
		 */
		Box bBenhNhan = Box.createVerticalBox();
		Box bBN1, bBN2, bBN3;
		Box bBN_TT, bBN_TT1, bBN_TT2, bBN_TT3, bBN_TT4, bBN_TT5;
		Box bBN_TK, bBN_TK1, bBN_TK2, bBN_CN;

		bBenhNhan.add(bBN1 = Box.createHorizontalBox());
		bBN1.setMaximumSize(new Dimension(1000, 1));

		bBN1.add(bBN_TT = Box.createVerticalBox());
		bBN_TT.setBorder(BorderFactory.createTitledBorder("Thông tin bệnh nhân"));

		bBN_TT.add(bBN_TT1 = Box.createHorizontalBox());
		bBN_TT.add(Box.createVerticalStrut(10));
		bBN_TT1.add(lblBenhNhanID = new JLabel("Mã số bệnh nhân"));
		bBN_TT1.add(Box.createHorizontalStrut(10));
		bBN_TT1.add(txtBenhNhanID = new JTextField());

		bBN_TT.add(bBN_TT2 = Box.createHorizontalBox());
		bBN_TT.add(Box.createVerticalStrut(10));
		bBN_TT2.add(lblHoBenhNhan = new JLabel("Họ"));
		bBN_TT2.add(Box.createHorizontalStrut(10));
		bBN_TT2.add(txtHoBenhNhan = new JTextField());

		bBN_TT.add(bBN_TT3 = Box.createHorizontalBox());
		bBN_TT.add(Box.createVerticalStrut(10));
		bBN_TT3.add(lblTenBenhNhan = new JLabel("Tên"));
		bBN_TT3.add(Box.createHorizontalStrut(10));
		bBN_TT3.add(txtTenBenhNhan = new JTextField());

		bBN_TT.add(bBN_TT4 = Box.createHorizontalBox());
		bBN_TT.add(Box.createVerticalStrut(10));
		bBN_TT4.add(lblSDTBenhNhan = new JLabel("Số điện thoại"));
		bBN_TT4.add(Box.createHorizontalStrut(10));
		bBN_TT4.add(txtSDTBenhNhan = new JTextField());

		bBN_TT.add(bBN_TT5 = Box.createHorizontalBox());
		bBN_TT5.add(lblDiaChiBenhNhan = new JLabel("Địa chỉ"));
		bBN_TT5.add(Box.createHorizontalStrut(10));
		bBN_TT5.add(txtDiaChiBenhNhan = new JTextField());

		lblTenBenhNhan.setPreferredSize(lblBenhNhanID.getPreferredSize());
		lblDiaChiBenhNhan.setPreferredSize(lblBenhNhanID.getPreferredSize());
		lblHoBenhNhan.setPreferredSize(lblBenhNhanID.getPreferredSize());
		lblSDTBenhNhan.setPreferredSize(lblBenhNhanID.getPreferredSize());

		// ***********************
//		bBN1.add(bBN_TK = Box.createVerticalBox());
//		bBN_TK.setBorder(BorderFactory.createTitledBorder("Chức năng"));
//		bBN_TK.add(bBN_TK1 = Box.createHorizontalBox());
//		String[] timKiem = { "Tìm theo ID", "Tìm theo tên", "Tìm theo số điện thoại" };
//		bBN_TK1.add(cbbTimKiemBenhNhan = new JComboBox<String>(timKiem));
//		bBN_TK1.add(Box.createHorizontalStrut(10));
//		bBN_TK1.add(txtTimKiemBenhNhan = new JTextField(1));
//		bBN_TK1.add(Box.createHorizontalStrut(10));
//		bBN_TK1.add(btnTimBenhNhan = new JButton("Tìm"));
//		bBN_TK.add(Box.createVerticalStrut(10));

//		bBN_TK.add(bBN_TK2 = Box.createHorizontalBox());
//		bBN_TK2.add(Box.createHorizontalGlue());
//		bBN_TK2.add(lblXoaBenhNhan = new JLabel("Xóa bệnh nhân hai năm chưa khám lại"));
//		lblXoaBenhNhan.setFont(new Font("serif", Font.PLAIN, 17));
//		bBN_TK2.add(Box.createHorizontalStrut(14));
//		bBN_TK2.add(btnXoaBenhNhan = new JButton("Xóa"));
//		bBN_TK.add(Box.createVerticalStrut(110));

		// ***********************
		bBenhNhan.add(bBN2 = Box.createVerticalBox());
		bBN2.add(bBN_CN = Box.createHorizontalBox());
		bBN_CN.add(Box.createHorizontalGlue());
		bBN_CN.add(lblXoaBenhNhan = new JLabel("Xóa bệnh nhân hai năm chưa khám lại"));
		lblXoaBenhNhan.setFont(new Font("serif", Font.PLAIN, 17));
		bBN_CN.add(Box.createHorizontalStrut(14));
		bBN_CN.add(btnXoaBenhNhan = new JButton("Xóa"));
		btnXoaBenhNhan.setMaximumSize(new Dimension(2000, 200));
		bBN_CN.add(Box.createHorizontalStrut(15));

//		bBN_CN.add(btnThemBenhNhan = new JButton("Thêm"));
//		bBN_CN.add(Box.createHorizontalStrut(10));
//		bBN_CN.add(btnSuaBenhNhan = new JButton("Sửa"));
//		bBN_CN.add(Box.createHorizontalStrut(10));
//		bBN_CN.add(btnLuuBenhNhan = new JButton("Lưu"));
//		bBN_CN.add(Box.createHorizontalStrut(10));
//		bBN_CN.add(btnXoaBenhNhan = new JButton("Xóa"));
//
//		btnThemBenhNhan.setMaximumSize(getMaximumSize());
//		btnXoaBenhNhan.setMaximumSize(getMaximumSize());
//		btnSuaBenhNhan.setMaximumSize(getMaximumSize());
//		btnLuuBenhNhan.setMaximumSize(getMaximumSize());

		// ***********************
		bBenhNhan.add(bBN3 = Box.createVerticalBox());
		bBN3.setBorder(BorderFactory.createTitledBorder("Danh sách bệnh nhân"));

		String[] headersBN = "Mã số bệnh nhân;Họ;Tên;Số điện thoại;Địa chỉ".split(";");
		modelBenhNhan = new DefaultTableModel(headersBN, 0);
		JScrollPane scrollBN = new JScrollPane();
		scrollBN.setViewportView(tableBenhNhan = new JTable(modelBenhNhan));
		tableBenhNhan.setRowHeight(20);
		tableBenhNhan.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		bBN3.add(scrollBN);

		/*
		 * Giao dien quan ly nhan vien
		 */
		Box bNhanVien = Box.createVerticalBox();
		Box bNV1, bNV2, bNV3;
		Box bNV_TT, bNV_TT1, bNV_TT2, bNV_TT3, bNV_TT4, bNV_TT5, bNV_TT6;
		Box bNV_TK, bNV_TK1, bNV_CN;

		bNhanVien.add(bNV1 = Box.createHorizontalBox());

		bNV1.add(bNV_TT = Box.createVerticalBox());
		bNV_TT.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));

		bNV_TT.add(bNV_TT1 = Box.createHorizontalBox());
		bNV_TT.add(Box.createVerticalStrut(10));
		bNV_TT1.add(lblNhanVienID = new JLabel("Mã số nhân viên"));
		bNV_TT1.add(Box.createHorizontalStrut(10));
		bNV_TT1.add(txtNhanVienID = new JTextField());

		bNV_TT.add(bNV_TT2 = Box.createHorizontalBox());
		bNV_TT.add(Box.createVerticalStrut(10));
		bNV_TT2.add(lblHoNhanVien = new JLabel("Họ"));
		bNV_TT2.add(Box.createHorizontalStrut(10));
		bNV_TT2.add(txtHoNhanVien = new JTextField());

		bNV_TT.add(bNV_TT3 = Box.createHorizontalBox());
		bNV_TT.add(Box.createVerticalStrut(10));
		bNV_TT3.add(lblTenNhanVien = new JLabel("Tên"));
		bNV_TT3.add(Box.createHorizontalStrut(10));
		bNV_TT3.add(txtTenNhanVien = new JTextField());

		bNV_TT.add(bNV_TT4 = Box.createHorizontalBox());
		bNV_TT.add(Box.createVerticalStrut(10));
		bNV_TT4.add(lblSDTNhanVien = new JLabel("Số điện thoại"));
		bNV_TT4.add(Box.createHorizontalStrut(10));
		bNV_TT4.add(txtSDTNhanVien = new JTextField());

		bNV_TT.add(bNV_TT5 = Box.createHorizontalBox());
		bNV_TT.add(Box.createVerticalStrut(10));
		bNV_TT5.add(lblDiaChiNhanVien = new JLabel("Địa chỉ"));
		bNV_TT5.add(Box.createHorizontalStrut(10));
		bNV_TT5.add(txtDiaChiNhanVien = new JTextField());

		bNV_TT.add(bNV_TT6 = Box.createHorizontalBox());
		bNV_TT6.add(lblNSNhanVien = new JLabel("Ngày sinh"));
		bNV_TT6.add(Box.createHorizontalStrut(10));
//		bNV_TT6.add(txtNSNhanVien = new JTextField());
		bNV_TT6.add(datePicker = new DatePicker());

		lblTenNhanVien.setPreferredSize(lblNhanVienID.getPreferredSize());
		lblDiaChiNhanVien.setPreferredSize(lblNhanVienID.getPreferredSize());
		lblHoNhanVien.setPreferredSize(lblNhanVienID.getPreferredSize());
		lblSDTNhanVien.setPreferredSize(lblNhanVienID.getPreferredSize());
		lblNSNhanVien.setPreferredSize(lblNhanVienID.getPreferredSize());

		// ***********************
		bNV1.add(bNV_TK = Box.createVerticalBox());
		bNV_TK.setMinimumSize(getMinimumSize());
		bNV_TK.setBorder(BorderFactory.createTitledBorder("Tìm kiếm nhân viên"));
		bNV_TK.add(bNV_TK1 = Box.createHorizontalBox());
		String[] timKiemNV = { "Tìm theo ID", "Tìm theo tên", "Tìm theo số điện thoại" };
		bNV_TK1.add(cbbTimNhanVien = new JComboBox<String>(timKiemNV));
		bNV_TK1.add(Box.createHorizontalStrut(10));
		bNV_TK1.add(txtTimNhanVien = new JTextField(1));
		bNV_TK1.add(Box.createHorizontalStrut(10));
		bNV_TK1.add(btnTimNV = new JButton("Tìm"));
		bNV_TK.add(Box.createVerticalStrut(185));

		// ***********************
		bNhanVien.add(bNV2 = Box.createVerticalBox());
		bNV2.add(bNV_CN = Box.createHorizontalBox());
		bNV2.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		bNV_CN.add(btnThemNV = new JButton("Thêm"));
		bNV_CN.add(Box.createHorizontalStrut(10));
		bNV_CN.add(btnSuaNV = new JButton("Sửa"));
		bNV_CN.add(Box.createHorizontalStrut(10));
		bNV_CN.add(btnLuuNV = new JButton("Lưu"));
		bNV_CN.add(Box.createHorizontalStrut(10));
		bNV_CN.add(btnCapNhat = new JButton("Cập nhật"));
		bNV_CN.add(Box.createHorizontalStrut(10));
		bNV_CN.add(btnXoaNV = new JButton("Xóa"));

		btnThemNV.setMaximumSize(getMaximumSize());
		btnSuaNV.setMaximumSize(getMaximumSize());
		btnLuuNV.setMaximumSize(getMaximumSize());
		btnXoaNV.setMaximumSize(getMaximumSize());
		btnCapNhat.setMaximumSize(getMaximumSize());

		// ***********************
		bNhanVien.add(bNV3 = Box.createVerticalBox());
		bNV3.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));

		String[] headersNV = "Mã số nhân viên;Họ;Tên;Số điện thoại;Địa chỉ;Ngày sinh".split(";");
		modelNhanVien = new DefaultTableModel(headersNV, 0);
		JScrollPane scrollNV = new JScrollPane();
		scrollNV.setViewportView(tableNhanVien = new JTable(modelNhanVien));
		tableNhanVien.setRowHeight(20);
		tableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		bNV3.add(scrollNV);

		btnLuuNV.setEnabled(false);

		/*
		 * Giao diện quan ly tai khoan
		 */
		Box bTaiKhoan = Box.createVerticalBox();
		Box bTK1, bTK2, bTK3;
		Box bTK1_1, bTK1_2, bTK1_3, bTK1_4, bTK_CN;

		bTaiKhoan.add(bTK1 = Box.createVerticalBox());
		bTK1.setBorder(BorderFactory.createTitledBorder("Thông tin tài khoản"));
		bTK1.setMaximumSize(new Dimension(1000, 200));

		bTK1.add(bTK1_4 = Box.createHorizontalBox());
		bTK1.add(Box.createVerticalStrut(10));
		bTK1_4.add(lblNVID = new JLabel("Mã nhân viên"));
		bTK1_4.add(Box.createHorizontalStrut(10));
		bTK1_4.add(txtNVID = new JTextField());

		bTK1.add(bTK1_1 = Box.createHorizontalBox());
		bTK1.add(Box.createVerticalStrut(10));
		bTK1_1.add(lblTenTaiKhoan = new JLabel("Tên tài khoản"));
		bTK1_1.add(Box.createHorizontalStrut(10));
		bTK1_1.add(txtTenTaiKhoan = new JTextField());

		bTK1.add(bTK1_2 = Box.createHorizontalBox());
		bTK1.add(Box.createVerticalStrut(10));
		bTK1_2.add(lblMatKhau = new JLabel("Mật khẩu"));
		bTK1_2.add(Box.createHorizontalStrut(10));
		bTK1_2.add(txtMatKhau = new JTextField());

		bTK1.add(bTK1_3 = Box.createHorizontalBox());
		bTK1_3.add(lblLoaiTaiKhoan = new JLabel("Loại tài khoản"));
		bTK1_3.add(Box.createHorizontalStrut(10));
		String[] loaiTaiKhoan = { "Nhân Viên Nhận Bệnh", "Bác Sĩ", "Nhân Viên Phát Thuốc", "Quản Lý" };
		bTK1_3.add(cbbLoaiTaiKhoan = new JComboBox<String>(loaiTaiKhoan));
		bTK1_3.add(Box.createHorizontalStrut(420));

		lblTenTaiKhoan.setPreferredSize(lblLoaiTaiKhoan.getPreferredSize());
		lblMatKhau.setPreferredSize(lblLoaiTaiKhoan.getPreferredSize());
		lblNVID.setPreferredSize(lblLoaiTaiKhoan.getPreferredSize());

		// ***********************
		bTaiKhoan.add(bTK2 = Box.createVerticalBox());
		bTK2.add(bTK_CN = Box.createHorizontalBox());
		bTK2.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		bTK2.setMaximumSize(new Dimension(1000, 200));
		bTK_CN.add(btnThemTaiKhoan = new JButton("Thêm"));
		bTK_CN.add(Box.createHorizontalStrut(10));
		bTK_CN.add(btnSuaTaiKhoan = new JButton("Sửa"));
		bTK_CN.add(Box.createHorizontalStrut(10));
		bTK_CN.add(btnLuuTaiKhoan = new JButton("Lưu"));
//		bTK_CN.add(Box.createHorizontalStrut(10));
//		bTK_CN.add(btnXoaTaiKhoan = new JButton("Xóa"));

		btnThemTaiKhoan.setMaximumSize(getMaximumSize());
		btnSuaTaiKhoan.setMaximumSize(getMaximumSize());
		btnLuuTaiKhoan.setMaximumSize(getMaximumSize());
//		btnXoaTaiKhoan.setMaximumSize(getMaximumSize());

		btnLuuTaiKhoan.setEnabled(false);

		// ***********************
		bTaiKhoan.add(bTK3 = Box.createVerticalBox());
		bTK3.setBorder(BorderFactory.createTitledBorder("Danh sách tài khoản"));

		String[] headersTK = "Mã nhân viên;Tên tài khoản;Mật khẩu;Loại tài khoản".split(";");
		modelTaiKhoan = new DefaultTableModel(headersTK, 0);
		JScrollPane scrollTK = new JScrollPane();
		scrollTK.setViewportView(tableTaiKhoan = new JTable(modelTaiKhoan));
		tableTaiKhoan.setRowHeight(20);
		tableTaiKhoan.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		bTK3.add(scrollTK);

		/*
		 * Giao dien quan ly thuoc
		 */
		Box bThuoc = Box.createVerticalBox();
		Box bT1, bT2;
		Box bT1_1, bT1_2, bT1_3;

		bThuoc.add(bT1 = Box.createVerticalBox());
		bT1.setBorder(BorderFactory.createTitledBorder("Thông tin thuốc"));

		bT1.add(bT1_1 = Box.createHorizontalBox());
		bT1.add(Box.createVerticalStrut(10));
		bT1_1.add(lblThuocID = new JLabel("Mã thuốc"));
		bT1_1.add(Box.createHorizontalStrut(10));
		bT1_1.add(txtThuocID = new JTextField());

		bT1.add(bT1_2 = Box.createHorizontalBox());
		bT1.add(Box.createVerticalStrut(10));
		bT1_2.add(lblTenThuoc = new JLabel("Tên thuốc"));
		bT1_2.add(Box.createHorizontalStrut(10));
		bT1_2.add(txtTenThuoc = new JTextField());

		bT1.add(bT1_3 = Box.createHorizontalBox());
		bT1_3.add(lblGiaThuoc = new JLabel("Đơn giá"));
		bT1_3.add(Box.createHorizontalStrut(10));
		bT1_3.add(txtGiaThuoc = new JTextField());
		bT1.setMaximumSize(new Dimension(1000, 200));

		lblThuocID.setPreferredSize(lblTenThuoc.getPreferredSize());
		lblGiaThuoc.setPreferredSize(lblTenThuoc.getPreferredSize());

		// ***********************
		bThuoc.add(bT2 = Box.createVerticalBox());
		bT2.setBorder(BorderFactory.createTitledBorder("Chi tiết toa thuốc"));

		String[] headersT = "Mã thuốc;Tên thuốc;Đơn giá".split(";");
		modelThuoc = new DefaultTableModel(headersT, 0);
		JScrollPane scrollT = new JScrollPane();
		scrollT.setViewportView(tableThuoc = new JTable(modelThuoc));
		tableThuoc.setRowHeight(20);
		tableThuoc.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		bT2.add(scrollT);

		/*
		 * Giao dien quan ly toa thuoc
		 */
		Box bToaThuoc = Box.createVerticalBox();
		Box bTT1, bTT2, bTT3;
		Box bTT1_1, bTT1_2, bTT1_3, bTT1_4;

		bToaThuoc.add(bTT1 = Box.createVerticalBox());
		bTT1.setBorder(BorderFactory.createTitledBorder("Thông tin toa thuốc"));

		bTT1.add(bTT1_1 = Box.createHorizontalBox());
		bTT1.add(Box.createVerticalStrut(10));
		bTT1_1.add(lblToaThuocID = new JLabel("Mã toa thuốc"));
		bTT1_1.add(Box.createHorizontalStrut(10));
		bTT1_1.add(txtToaThuocID = new JTextField());

		bTT1.add(bTT1_2 = Box.createHorizontalBox());
		bTT1.add(Box.createVerticalStrut(10));
		bTT1_2.add(lblDonGia = new JLabel("Đơn giá"));
		bTT1_2.add(Box.createHorizontalStrut(10));
		bTT1_2.add(txtDonGia = new JTextField());

		bTT1.add(bTT1_3 = Box.createHorizontalBox());
		bTT1.add(Box.createVerticalStrut(10));
		bTT1_3.add(lblGhiChu = new JLabel("Ghi chú"));
		bTT1_3.add(Box.createHorizontalStrut(10));
		bTT1_3.add(txtGhiChu = new JTextField());

		bTT1.add(bTT1_4 = Box.createHorizontalBox());
		bTT1_4.add(lblTrangThai = new JLabel("Trạng thái"));
		bTT1_4.add(Box.createHorizontalStrut(10));
		bTT1_4.add(txtTrangThai = new JTextField());

		lblGhiChu.setPreferredSize(lblToaThuocID.getPreferredSize());
		lblDonGia.setPreferredSize(lblToaThuocID.getPreferredSize());
		lblTrangThai.setPreferredSize(lblToaThuocID.getPreferredSize());
		txtTrangThai.setMinimumSize(txtToaThuocID.getPreferredSize());

		// ***********************
		bToaThuoc.add(bTT2 = Box.createVerticalBox());
		bTT2.setBorder(BorderFactory.createTitledBorder("Chi tiết toa thuốc"));

		String[] headersCTTT = "Mã thuốc;Tên thuốc;Đơn giá;Số lượng;Tổng giá".split(";");
		modelChiTietToa = new DefaultTableModel(headersCTTT, 0);
		JScrollPane scrollCTTT = new JScrollPane();
		scrollCTTT.setViewportView(tableChiTietToa = new JTable(modelChiTietToa));
		tableChiTietToa.setRowHeight(20);
		tableChiTietToa.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		bTT2.add(scrollCTTT);

		// ***********************
		bToaThuoc.add(bTT3 = Box.createVerticalBox());
		bTT3.setBorder(BorderFactory.createTitledBorder("Danh sách toa thuốc"));

		String[] headersTT = "Mã toa thuốc;Đơn giá;Ghi chú;Trạng thái".split(";");
		modelToaThuoc = new DefaultTableModel(headersTT, 0);
		JScrollPane scrollTT = new JScrollPane();
		scrollTT.setViewportView(tableToaThuoc = new JTable(modelToaThuoc));
		tableToaThuoc.setRowHeight(20);
		tableToaThuoc.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		bTT3.add(scrollTT);

		/*
		 * Giao dien quan ly ca nhan
		 */
		JTabbedPane tabCaNhan = new JTabbedPane();

		Box bCaNhan = Box.createVerticalBox();
		Box bCN1, bCN2, bCN3, bCN4, bCN5, bCN6;

		bCaNhan.add(Box.createVerticalStrut(10));
		bCaNhan.add(bCN1 = Box.createHorizontalBox());
		bCaNhan.add(Box.createVerticalStrut(10));
		bCN1.setMaximumSize(new Dimension(1000, 30));
		bCN1.add(Box.createHorizontalStrut(20));
		bCN1.add(lblCaNhanID = new JLabel("Mã số nhân viên"));
		bCN1.add(Box.createHorizontalStrut(10));
		bCN1.add(txtCaNhanID = new JTextField());
		bCN1.add(Box.createHorizontalStrut(500));

		bCaNhan.add(bCN2 = Box.createHorizontalBox());
		bCaNhan.add(Box.createVerticalStrut(10));
		bCN2.setMaximumSize(new Dimension(1000, 30));
		bCN2.add(Box.createHorizontalStrut(20));
		bCN2.add(lblHoCaNhan = new JLabel("Họ"));
		bCN2.add(Box.createHorizontalStrut(10));
		bCN2.add(txtHoCaNhan = new JTextField());
		bCN2.add(Box.createHorizontalStrut(500));

		bCaNhan.add(bCN3 = Box.createHorizontalBox());
		bCaNhan.add(Box.createVerticalStrut(10));
		bCN3.setMaximumSize(new Dimension(1000, 30));
		bCN3.add(Box.createHorizontalStrut(20));
		bCN3.add(lblTenCaNhan = new JLabel("Tên"));
		bCN3.add(Box.createHorizontalStrut(10));
		bCN3.add(txtTenCaNhan = new JTextField());
		bCN3.add(Box.createHorizontalStrut(500));

		bCaNhan.add(bCN4 = Box.createHorizontalBox());
		bCaNhan.add(Box.createVerticalStrut(10));
		bCN4.setMaximumSize(new Dimension(1000, 30));
		bCN4.add(Box.createHorizontalStrut(20));
		bCN4.add(lblSDTCaNhan = new JLabel("Số điện thoại"));
		bCN4.add(Box.createHorizontalStrut(10));
		bCN4.add(txtSDTCaNhan = new JTextField());
		bCN4.add(Box.createHorizontalStrut(500));

		bCaNhan.add(bCN5 = Box.createHorizontalBox());
		bCaNhan.add(Box.createVerticalStrut(10));
		bCN5.setMaximumSize(new Dimension(1000, 30));
		bCN5.add(Box.createHorizontalStrut(20));
		bCN5.add(lblDiaChiCaNhan = new JLabel("Địa chỉ"));
		bCN5.add(Box.createHorizontalStrut(10));
		bCN5.add(txtDiaChiCaNhan = new JTextField());
		bCN5.add(Box.createHorizontalStrut(500));

		bCaNhan.add(bCN6 = Box.createHorizontalBox());
		bCN6.setMaximumSize(new Dimension(1000, 30));
		bCN6.add(Box.createHorizontalStrut(20));
		bCN6.add(lblNSCaNhan = new JLabel("Ngày sinh"));
		bCN6.add(Box.createHorizontalStrut(10));
		bCN6.add(txtNSCaNhan = new JTextField());
		bCN6.add(Box.createHorizontalStrut(500));

		lblTenCaNhan.setPreferredSize(lblCaNhanID.getPreferredSize());
		lblDiaChiCaNhan.setPreferredSize(lblCaNhanID.getPreferredSize());
		lblHoCaNhan.setPreferredSize(lblCaNhanID.getPreferredSize());
		lblSDTCaNhan.setPreferredSize(lblCaNhanID.getPreferredSize());
		lblNSCaNhan.setPreferredSize(lblCaNhanID.getPreferredSize());

		// ***********************
		Box bMatKhau = Box.createVerticalBox();
		Box bMK1, bMK2, bMK3, bMK4;

		bMatKhau.add(Box.createVerticalStrut(10));
		bMatKhau.add(bMK1 = Box.createHorizontalBox());
		bMatKhau.add(Box.createVerticalStrut(10));
		bMK1.setMaximumSize(new Dimension(1000, 30));
		bMK1.add(Box.createHorizontalStrut(20));
		bMK1.add(lblMatKhauCu = new JLabel("Nhập mật khẩu cũ"));
		bMK1.add(Box.createHorizontalStrut(10));
		bMK1.add(pwfMatKhauCu = new JPasswordField());
		bMK1.add(Box.createHorizontalStrut(500));

		bMatKhau.add(bMK2 = Box.createHorizontalBox());
		bMatKhau.add(Box.createVerticalStrut(10));
		bMK2.setMaximumSize(new Dimension(1000, 30));
		bMK2.add(Box.createHorizontalStrut(20));
		bMK2.add(lblMatKhauMoi = new JLabel("Nhập mật mới"));
		bMK2.add(Box.createHorizontalStrut(10));
		bMK2.add(pwfMatKhauMoi = new JPasswordField());
		bMK2.add(Box.createHorizontalStrut(500));

		bMatKhau.add(bMK3 = Box.createHorizontalBox());
		bMatKhau.add(Box.createVerticalStrut(10));
		bMK3.setMaximumSize(new Dimension(1000, 30));
		bMK3.add(Box.createHorizontalStrut(20));
		bMK3.add(lblNhapLaiMK = new JLabel("Nhập lại mật khẩu"));
		bMK3.add(Box.createHorizontalStrut(10));
		bMK3.add(pwfNhapLaiMK = new JPasswordField());
		bMK3.add(Box.createHorizontalStrut(500));

		bMatKhau.add(bMK4 = Box.createHorizontalBox());
		bMatKhau.add(Box.createVerticalStrut(1000));
		bMK4.add(btnDoi = new JButton("Đổi"));
		bMK4.add(Box.createHorizontalStrut(30));
		bMK4.add(btnHuy = new JButton("Hủy"));
		bMK4.add(Box.createHorizontalStrut(10));
		bMK4.add(Box.createHorizontalStrut(530));

		lblMatKhauMoi.setPreferredSize(lblMatKhauCu.getPreferredSize());
		lblNhapLaiMK.setPreferredSize(lblMatKhauCu.getPreferredSize());

		tabCaNhan.addTab("Thông tin cá nhân", bCaNhan);
		tabCaNhan.addTab("Đổi mật khẩu", bMatKhau);

		/*
		 * Phan ket noi tab va gan su kien
		 */
		tab.addTab("Quản lý bệnh nhân", bBenhNhan);
		tab.addTab("Quản lý nhân viên", bNhanVien);
		tab.addTab("Quản lý tài khoản", bTaiKhoan);
		tab.addTab("Quản lý thuốc", bThuoc);
		tab.addTab("Quản lý toa thuốc", bToaThuoc);
		tab.addTab("Quản lý cá nhân", tabCaNhan);
		add(tab, BorderLayout.CENTER);
		loadData();

		setJtable(tableBenhNhan);
		loadTextField(BenhNhan.class);
		setJtable(tableToaThuoc);
		loadTextField(ToaThuoc.class);
		setJtable(tableThuoc);
		loadTextField(Thuoc.class);
		setJtable(tableNhanVien);
		loadTextField(NhanVien.class);
		setJtable(tableTaiKhoan);
		loadTextField(TaiKhoan.class);

		btnXoaBenhNhan.addActionListener(this);

		tableToaThuoc.addMouseListener(this);
		tableBenhNhan.addMouseListener(this);
		tableTaiKhoan.addMouseListener(this);
		tableThuoc.addMouseListener(this);
		tableNhanVien.addMouseListener(this);

		btnThemNV.addActionListener(this);
		btnSuaNV.addActionListener(this);
		btnXoaNV.addActionListener(this);
		btnLuuNV.addActionListener(this);
		btnTimNV.addActionListener(this);
		btnCapNhat.addActionListener(this);

		btnThemTaiKhoan.addActionListener(this);
		btnSuaTaiKhoan.addActionListener(this);
//		btnXoaTaiKhoan.addActionListener(this);
		btnLuuTaiKhoan.addActionListener(this);

		btnDong.addActionListener(this);
		
		btnDoi.addActionListener(this);
		btnHuy.addActionListener(this);
		nonEditable();
		txtCaNhanID.setText(nhanVien.getIDNhanVien());
//		txtHoCaNhan.setText(nhanVien.getHo());
//		txtTenCaNhan.setText(nhanVien.getTen());
//		txtDiaChiCaNhan.setText(nhanVien.getDiaChi());
//		txtSDTCaNhan.setText(nhanVien.getSDT());
//		txtNSCaNhan.setText(nhanVien.getNgaySinh().toString());
		Loadthongtinnhanvien();
		setVisible(true);
	}
	private void Loadthongtinnhanvien()
	{
		txtCaNhanID.setText(nhanVien.getIDNhanVien());
		txtHoCaNhan.setText(nhanVien.getHo());
		txtTenCaNhan.setText(nhanVien.getTen());
		txtDiaChiCaNhan.setText(nhanVien.getDiaChi());
		txtSDTCaNhan.setText(nhanVien.getSDT());
		txtNSCaNhan.setText(nhanVien.getNgaySinh().toString());
	}
	private boolean DoiMatKhau()
	{
		String mkc= "",mkm = "",nlmkm = "";
		char a[],b[],c[];
		a = pwfMatKhauCu.getPassword();
		b = pwfMatKhauMoi.getPassword();
		c = pwfNhapLaiMK.getPassword();
		for (int i = 0; i < a.length; i++) {
			mkc = mkc + a[i];
		}
		for (int i = 0; i < b.length; i++) {
			mkm = mkm + b[i];
		}
		for (int i = 0; i < c.length; i++) {
			nlmkm = nlmkm + c[i];
		}
		if(!quanLyDaos.CheckMatKhauCu(nhanVien, mkc)) {
			JOptionPane.showMessageDialog(new JFrame(),"Mật khẩu cũ không khớp");
			XoaTrangDoiMatKhau();
			return false;
		}else if(!mkm.equals(nlmkm)) {
			JOptionPane.showMessageDialog(new JFrame(),"Nhập lại mật khẩu mới không khớp");
			XoaTrangDoiMatKhau();
			return false;
		}else if(!quanLyDaos.DoiMatKhau(nhanVien, nlmkm)) {
			JOptionPane.showMessageDialog(new JFrame(),"Đổi mật khẩu không thành công");
			XoaTrangDoiMatKhau();
			return false;
		}
		JOptionPane.showMessageDialog(new JFrame(),"Đổi mật khẩu thành công");
		new Gui_DangNhap();
		dispose();
		return true;
	}
	private void XoaTrangDoiMatKhau() {
		pwfMatKhauCu.setText("");
		pwfMatKhauMoi.setText("");
		pwfNhapLaiMK.setText("");
	}

	private void setJtable(JTable table) {
		if (table.getRowCount() >= 0) {
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getSelectionModel().setSelectionInterval(0, 0);
		}
	}

	private void nonEditable() {
		txtBenhNhanID.setEditable(false);
		txtHoBenhNhan.setEditable(false);
		txtDiaChiBenhNhan.setEditable(false);
		txtSDTBenhNhan.setEditable(false);
		txtTenBenhNhan.setEditable(false);

		txtGhiChu.setEditable(false);
		txtDonGia.setEditable(false);
		txtToaThuocID.setEditable(false);
		txtTrangThai.setEditable(false);

		txtGiaThuoc.setEditable(false);
		txtTenThuoc.setEditable(false);
		txtThuocID.setEditable(false);

		txtNhanVienID.setEditable(false);
		txtHoNhanVien.setEditable(false);
		txtTenNhanVien.setEditable(false);
		txtSDTNhanVien.setEditable(false);
//		txtNSNhanVien.setEditable(false);
//		datePicker.setEnabled(false);
		txtDiaChiNhanVien.setEditable(false);

		txtTenTaiKhoan.setEditable(false);
		txtMatKhau.setEditable(false);
		txtNVID.setEditable(false);
	}

	@SuppressWarnings("rawtypes")
	private void editable(Class t) {
		if (t.equals(NhanVien.class)) {
			txtNhanVienID.setEditable(true);
			txtHoNhanVien.setEditable(true);
			txtTenNhanVien.setEditable(true);
			txtSDTNhanVien.setEditable(true);
//			txtNSNhanVien.setEditable(false);
//			datePicker.setEnabled(false);
			txtDiaChiNhanVien.setEditable(true);
		} else if (t.equals(TaiKhoan.class)) {
			txtTenTaiKhoan.setEditable(true);
			txtMatKhau.setEditable(true);
			txtNVID.setEditable(true);
		}
	}

	@SuppressWarnings("rawtypes")
	private void xoaTextField(Class t) {
		if (t.equals(NhanVien.class)) {
			txtNhanVienID.setText("");
			txtHoNhanVien.setText("");
			txtTenNhanVien.setText("");
			txtSDTNhanVien.setText("");
			txtDiaChiNhanVien.setText("");
//			txtNSNhanVien.setText("");
			datePicker.setDate(null);
		} else if (t.equals(TaiKhoan.class)) {
			txtTenTaiKhoan.setText("");
			txtMatKhau.setText("");
			txtNVID.setText("");
		}
	}

	@SuppressWarnings("rawtypes")
	private void loadTextField(Class t) {
		int row = 0;

		if (t.equals(BenhNhan.class)) {
			row = tableBenhNhan.getSelectedRow();

			txtBenhNhanID.setText(tableBenhNhan.getValueAt(row, 0).toString());
			txtHoBenhNhan.setText(tableBenhNhan.getValueAt(row, 1).toString());
			txtTenBenhNhan.setText(tableBenhNhan.getValueAt(row, 2).toString());
			txtSDTBenhNhan.setText(tableBenhNhan.getValueAt(row, 3).toString());
			txtDiaChiBenhNhan.setText(tableBenhNhan.getValueAt(row, 4).toString());
		} else if (t.equals(NhanVien.class)) {
			row = tableNhanVien.getSelectedRow();

			txtNhanVienID.setText(tableNhanVien.getValueAt(row, 0).toString());
			txtHoNhanVien.setText(tableNhanVien.getValueAt(row, 1).toString());
			txtTenNhanVien.setText(tableNhanVien.getValueAt(row, 2).toString());
			txtSDTNhanVien.setText(tableNhanVien.getValueAt(row, 3).toString());
			txtDiaChiNhanVien.setText(tableNhanVien.getValueAt(row, 4).toString());
//			txtNSNhanVien.setText(tableNhanVien.getValueAt(row, 5).toString());
			LocalDate date = LocalDate.parse(tableNhanVien.getValueAt(row, 5).toString());
			datePicker.setDate(date);
		} else if (t.equals(TaiKhoan.class)) {
			row = tableTaiKhoan.getSelectedRow();

			txtNVID.setText(tableTaiKhoan.getValueAt(row, 0).toString());
			txtTenTaiKhoan.setText(tableTaiKhoan.getValueAt(row, 1).toString());
			txtMatKhau.setText(tableTaiKhoan.getValueAt(row, 2).toString());
			String s = tableTaiKhoan.getValueAt(row, 3).toString();

			if (s.equalsIgnoreCase("Nhân Viên Nhận Bệnh")) {
				cbbLoaiTaiKhoan.setSelectedIndex(0);
			} else if (s.equalsIgnoreCase("Bác Sỹ")) {
				cbbLoaiTaiKhoan.setSelectedIndex(1);
			} else if (s.equalsIgnoreCase("Quản Lý")) {
				cbbLoaiTaiKhoan.setSelectedIndex(3);
			} else if (s.equalsIgnoreCase("Nhân viên phát thuốc")) {
				cbbLoaiTaiKhoan.setSelectedIndex(2);
			}

		} else if (t.equals(Thuoc.class)) {
			row = tableThuoc.getSelectedRow();

			txtThuocID.setText(tableThuoc.getValueAt(row, 0).toString());
			txtTenThuoc.setText(tableThuoc.getValueAt(row, 1).toString());
			txtGiaThuoc.setText(tableThuoc.getValueAt(row, 2).toString());
		} else if (t.equals(ToaThuoc.class)) {
			row = tableToaThuoc.getSelectedRow();

			txtToaThuocID.setText(tableToaThuoc.getValueAt(row, 0).toString());
			txtDonGia.setText(tableToaThuoc.getValueAt(row, 1).toString());
			txtGhiChu.setText(tableToaThuoc.getValueAt(row, 2).toString());
			txtTrangThai.setText(tableToaThuoc.getValueAt(row, 3).toString());
		}
	}

	private void loadData() {
		dsBenhNhan = quanLyDaos.getAllBenhNhan();
		for (int i = 0; i < dsBenhNhan.size(); i++) {
			String s[] = { dsBenhNhan.get(i).getIDBenhNhan(), dsBenhNhan.get(i).getHo(), dsBenhNhan.get(i).getTenBN(),
					dsBenhNhan.get(i).getSDT(), dsBenhNhan.get(i).getDiaChi() };
			modelBenhNhan.addRow(s);
		}

		dsNhanVien = quanLyDaos.getAllNhanVien();
		for (int i = 0; i < dsNhanVien.size(); i++) {
			String s[] = { dsNhanVien.get(i).getIDNhanVien(), dsNhanVien.get(i).getHo(), dsNhanVien.get(i).getTen(),
					dsNhanVien.get(i).getSDT(), dsNhanVien.get(i).getDiaChi(),
					dsNhanVien.get(i).getNgaySinh().toString() };
			modelNhanVien.addRow(s);
		}

		dsTaiKhoan = quanLyDaos.getAllTaiKhoan();
		for (int i = 0; i < dsTaiKhoan.size(); i++) {
			String s[] = { dsTaiKhoan.get(i).getIDTaiKhoan(), dsTaiKhoan.get(i).getUsername(),
					dsTaiKhoan.get(i).getMatkhau(), dsTaiKhoan.get(i).getLoai() };
			modelTaiKhoan.addRow(s);
		}

		dsThuoc = quanLyDaos.getAllThuoc();
		for (int i = 0; i < dsThuoc.size(); i++) {
			String s[] = { dsThuoc.get(i).getIDThuoc(), dsThuoc.get(i).getTenThuoc(),
					Double.toString(dsThuoc.get(i).getDonGia()) };
			modelThuoc.addRow(s);
		}

		dsToaThuoc = quanLyDaos.getAllToaThuoc();
		for (int i = 0; i < dsToaThuoc.size(); i++) {
			String trangthai = "";
			String tongGia = "";
			tongGia = Double.toString(quanLyDaos.getTongGiaToaThuocTheoID(dsToaThuoc, i));
			if (dsToaThuoc.get(i).isTrangThai() == false) {
				trangthai = "Chưa phát";
			} else {
				trangthai = "Đã phát";
			}
			String s[] = { dsToaThuoc.get(i).getIDToaThuoc(), tongGia, dsToaThuoc.get(i).getGhiChu(), trangthai };
			modelToaThuoc.addRow(s);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();

		if (object.equals(btnThemNV)) { // Nhan vien
			if (btnThemNV.getText().equalsIgnoreCase("thêm")) {
				btnThemNV.setText("Hủy");
				btnLuuNV.setEnabled(true);
				btnSuaNV.setEnabled(false);
				btnXoaNV.setEnabled(false);
				tableNhanVien.setEnabled(false);
				xoaTextField(NhanVien.class);
				txtNhanVienID.requestFocus();
				btnCapNhat.setEnabled(false);
				btnTimNV.setEnabled(false);
				editable(NhanVien.class);
			} else {
				btnThemNV.setText("Thêm");
				btnLuuNV.setEnabled(false);
				btnSuaNV.setEnabled(true);
				btnXoaNV.setEnabled(true);
				tableNhanVien.setEnabled(true);
				loadTextField(NhanVien.class);
				btnCapNhat.setEnabled(true);
				btnTimNV.setEnabled(true);
				nonEditable();
			}
		} else if (object.equals(btnSuaNV)) {
			if (btnSuaNV.getText().equalsIgnoreCase("sửa")) {
				btnSuaNV.setText("Hủy");
				btnLuuNV.setEnabled(true);
				btnThemNV.setEnabled(false);
				btnXoaNV.setEnabled(false);
				tableNhanVien.setEnabled(false);
				btnCapNhat.setEnabled(false);
				btnTimNV.setEnabled(false);
				txtHoNhanVien.requestFocus();
				editable(NhanVien.class);
				txtNhanVienID.setEnabled(false);
			} else {
				btnSuaNV.setText("Sửa");
				loadTextField(NhanVien.class);
				btnLuuNV.setEnabled(false);
				btnThemNV.setEnabled(true);
				btnXoaNV.setEnabled(true);
				tableNhanVien.setEnabled(true);
				btnCapNhat.setEnabled(true);
				btnTimNV.setEnabled(true);
				txtNhanVienID.setEnabled(true);
				nonEditable();
			}
		} else if (object.equals(btnXoaNV)) {
			int row = tableNhanVien.getSelectedRow();

			if (quanLyDaos.xoaNhanVienTheoID(txtNhanVienID.getText())) {
				modelNhanVien.removeRow(row);

				modelTaiKhoan.setRowCount(0);
				dsTaiKhoan = quanLyDaos.getAllTaiKhoan();
				for (int i = 0; i < dsTaiKhoan.size(); i++) {
					String s[] = { dsTaiKhoan.get(i).getIDTaiKhoan(), dsTaiKhoan.get(i).getUsername(),
							dsTaiKhoan.get(i).getMatkhau(), dsTaiKhoan.get(i).getLoai() };
					modelTaiKhoan.addRow(s);
				}

				JOptionPane.showMessageDialog(null, "Xóa thành công", "", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Xóa không thành công", "", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (object.equals(btnLuuNV)) {
			if (btnThemNV.getText().equalsIgnoreCase("hủy")) {
				if (!txtNhanVienID.getText().isEmpty()) {
					NhanVien nhanVien = new NhanVien();
					nhanVien.setIDNhanVien(txtNhanVienID.getText().trim());
					nhanVien.setTen(txtTenNhanVien.getText().trim());
					nhanVien.setHo(txtHoNhanVien.getText().trim());
					nhanVien.setSDT(txtSDTNhanVien.getText().trim());
					nhanVien.setDiaChi(txtDiaChiNhanVien.getText().trim());
					LocalDate date = datePicker.getDate();
					if (date == null)
						date = LocalDate.now();
					nhanVien.setNgaySinh(date);

					if (quanLyDaos.themNhanVien(nhanVien)) {

						String s[] = { txtNhanVienID.getText().trim(), txtHoNhanVien.getText().trim(),
								txtTenNhanVien.getText().trim(), txtSDTNhanVien.getText().trim(),
								txtDiaChiNhanVien.getText().trim(), date.toString() };
						modelNhanVien.addRow(s);
						JOptionPane.showMessageDialog(null, "Thêm thành công", "", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Thêm không thành công", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
					btnThemNV.setText("Thêm");
					btnLuuNV.setEnabled(false);
					btnSuaNV.setEnabled(true);
					btnXoaNV.setEnabled(true);
					tableNhanVien.setEnabled(true);
					tableNhanVien.getSelectionModel().setSelectionInterval(0, 0);
					loadTextField(NhanVien.class);
					btnCapNhat.setEnabled(true);
					btnTimNV.setEnabled(true);
					nonEditable();
				} else {
					JOptionPane.showMessageDialog(null, "Mã nhân viên không được để trống", "",
							JOptionPane.INFORMATION_MESSAGE);
					txtNhanVienID.requestFocus();
				}
			} else {
				if (!txtNhanVienID.getText().isEmpty()) {
					String maNV = txtNhanVienID.getText().trim();
					String tenNV = txtTenNhanVien.getText().trim();
					String hoNV = txtHoNhanVien.getText().trim();
					String sdtNV = txtSDTNhanVien.getText().trim();
					String diaChi = txtDiaChiNhanVien.getText().trim();
					LocalDate date = datePicker.getDate();

					if (date == null)
						date = LocalDate.now();

					NhanVien nhanVien = new NhanVien(maNV, tenNV, hoNV, diaChi, sdtNV, date);
					if (quanLyDaos.suaThongTinNhanVien(nhanVien)) {
						modelNhanVien.setRowCount(0);
						dsNhanVien = quanLyDaos.getAllNhanVien();
						for (int i = 0; i < dsNhanVien.size(); i++) {
							String s[] = { dsNhanVien.get(i).getIDNhanVien(), dsNhanVien.get(i).getHo(),
									dsNhanVien.get(i).getTen(), dsNhanVien.get(i).getSDT(),
									dsNhanVien.get(i).getDiaChi(), dsNhanVien.get(i).getNgaySinh().toString() };
							modelNhanVien.addRow(s);
						}
						JOptionPane.showMessageDialog(null, "Sửa thành công", "", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Sửa không thành công", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
					btnSuaNV.setText("Sửa");
					btnLuuNV.setEnabled(false);
					btnThemNV.setEnabled(true);
					btnXoaNV.setEnabled(true);
					tableNhanVien.setEnabled(true);
					btnCapNhat.setEnabled(true);
					btnTimNV.setEnabled(true);
					nonEditable();
				}
			}
		} else if (object.equals(btnThemTaiKhoan)) {
			if (btnThemTaiKhoan.getText().equalsIgnoreCase("thêm")) {
				btnThemTaiKhoan.setText("Hủy");
				btnLuuTaiKhoan.setEnabled(true);
				btnSuaTaiKhoan.setEnabled(false);
//				btnXoaTaiKhoan.setEnabled(false);
				tableTaiKhoan.setEnabled(false);
				xoaTextField(TaiKhoan.class);
				txtNVID.requestFocus();
				editable(TaiKhoan.class);
			} else {
				btnThemTaiKhoan.setText("Thêm");
				btnLuuTaiKhoan.setEnabled(false);
				btnSuaTaiKhoan.setEnabled(true);
//				btnXoaTaiKhoan.setEnabled(true);
				tableTaiKhoan.setEnabled(true);
				loadTextField(TaiKhoan.class);
				nonEditable();
			}
		} else if (object.equals(btnSuaTaiKhoan)) {
			if (btnSuaTaiKhoan.getText().equalsIgnoreCase("sửa")) {
				btnSuaTaiKhoan.setText("Hủy");
				btnThemTaiKhoan.setEnabled(false);
				btnLuuTaiKhoan.setEnabled(true);
//				btnXoaTaiKhoan.setEnabled(false);
				tableTaiKhoan.setEnabled(false);
				editable(TaiKhoan.class);
				txtNVID.setEnabled(false);
				txtTenTaiKhoan.requestFocus();
			} else {
				btnSuaTaiKhoan.setText("Sửa");
				btnLuuTaiKhoan.setEnabled(false);
				btnThemTaiKhoan.setEnabled(true);
				btnXoaTaiKhoan.setEnabled(true);
				tableTaiKhoan.setEnabled(true);
				txtNVID.setEnabled(true);
				loadTextField(TaiKhoan.class);
				nonEditable();
			}
		} else if (object.equals(btnLuuTaiKhoan)) {
			if (btnThemTaiKhoan.getText().equalsIgnoreCase("hủy")) {
				TaiKhoan taiKhoan = new TaiKhoan();

				if (txtTenTaiKhoan.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tên tài khoản không được để trống", "",
							JOptionPane.INFORMATION_MESSAGE);
					txtTenTaiKhoan.requestFocus();
				} else if (txtMatKhau.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống", "",
							JOptionPane.INFORMATION_MESSAGE);
					txtMatKhau.requestFocus();
				} else if (txtNhanVienID.getText().trim() != "") {
					taiKhoan.setIDTaiKhoan(txtNVID.getText().trim());
					taiKhoan.setUsername(txtTenTaiKhoan.getText().trim());
					taiKhoan.setMatkhau(txtMatKhau.getText().trim());
					taiKhoan.setLoai(cbbLoaiTaiKhoan.getSelectedItem().toString());
					taiKhoan.setNgayBatDau(LocalDate.now());

					NhanVien nhanVien = quanLyDaos.getNhanVienTheoID(txtNVID.getText().trim());
					taiKhoan.setNhanVien(nhanVien);

					if (quanLyDaos.themTaiKhoan(taiKhoan)) {
						JOptionPane.showMessageDialog(null, "Thêm thành công", "", JOptionPane.INFORMATION_MESSAGE);

						String s[] = { taiKhoan.getIDTaiKhoan(), taiKhoan.getUsername(), taiKhoan.getMatkhau(),
								taiKhoan.getLoai() };
						modelTaiKhoan.addRow(s);
					} else {
						JOptionPane.showMessageDialog(null, "Thêm không thành công", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
					btnThemTaiKhoan.setText("Thêm");
					btnLuuTaiKhoan.setEnabled(false);
					btnSuaTaiKhoan.setEnabled(true);
					btnXoaTaiKhoan.setEnabled(true);
					tableTaiKhoan.setEnabled(true);
					loadTextField(TaiKhoan.class);
					nonEditable();
				} else {
					JOptionPane.showMessageDialog(null, "Mã tài khoản không được để trống", "",
							JOptionPane.INFORMATION_MESSAGE);
					txtNhanVienID.requestFocus();
				}
			} else {
				TaiKhoan taiKhoan = new TaiKhoan();

				if (txtTenTaiKhoan.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tên tài khoản không được để trống", "",
							JOptionPane.INFORMATION_MESSAGE);
					txtTenTaiKhoan.requestFocus();
				} else if (txtMatKhau.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống", "",
							JOptionPane.INFORMATION_MESSAGE);
					txtMatKhau.requestFocus();
				} else if (txtNhanVienID.getText().trim() != "") {
					taiKhoan.setIDTaiKhoan(txtNVID.getText());
					taiKhoan.setUsername(txtTenTaiKhoan.getText());
					taiKhoan.setMatkhau(txtMatKhau.getText());
					taiKhoan.setLoai(cbbLoaiTaiKhoan.getSelectedItem().toString());
					taiKhoan.setNgayBatDau(LocalDate.now());

					NhanVien nhanVien = quanLyDaos.getNhanVienTheoID(txtNVID.getText());
					taiKhoan.setNhanVien(nhanVien);

					if (quanLyDaos.suaThongTinTaiKhoan(taiKhoan)) {
						modelTaiKhoan.setRowCount(0);
						dsTaiKhoan = quanLyDaos.getAllTaiKhoan();
						for (int i = 0; i < dsTaiKhoan.size(); i++) {
							String s[] = { dsTaiKhoan.get(i).getIDTaiKhoan(), dsTaiKhoan.get(i).getUsername(),
									dsTaiKhoan.get(i).getMatkhau(), dsTaiKhoan.get(i).getLoai() };
							modelTaiKhoan.addRow(s);
						}
						JOptionPane.showMessageDialog(null, "Thêm thành công", "", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Thêm không thành công", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
					btnSuaTaiKhoan.setText("Sửa");
					btnLuuTaiKhoan.setEnabled(false);
					btnThemTaiKhoan.setEnabled(true);
					btnXoaTaiKhoan.setEnabled(true);
					tableTaiKhoan.setEnabled(true);
					nonEditable();
				}
			}
		} 
//		else if (object.equals(btnXoaTaiKhoan)) {
//			int row = tableTaiKhoan.getSelectedRow();
//
//			if (quanLyDaos.xoaTaiKhoanTheoID(txtNVID.getText())) {
//				JOptionPane.showMessageDialog(null, "Xóa thành công", "", JOptionPane.INFORMATION_MESSAGE);
//				modelTaiKhoan.removeRow(row);
//			} else {
//				JOptionPane.showMessageDialog(null, "Xóa không thành công", "", JOptionPane.INFORMATION_MESSAGE);
//			}
//		} 
		else if (object.equals(btnCapNhat)) {
			modelNhanVien.setRowCount(0);
			dsNhanVien = quanLyDaos.getAllNhanVien();
			for (int i = 0; i < dsNhanVien.size(); i++) {
				String s[] = { dsNhanVien.get(i).getIDNhanVien(), dsNhanVien.get(i).getHo(), dsNhanVien.get(i).getTen(),
						dsNhanVien.get(i).getSDT(), dsNhanVien.get(i).getDiaChi(),
						dsNhanVien.get(i).getNgaySinh().toString() };
				modelNhanVien.addRow(s);
			}
		} else if (object.equals(btnTimNV)) {
			if (cbbTimNhanVien.getSelectedIndex() == 0 && !txtTimNhanVien.getText().isEmpty()) {
				dsNhanVien = quanLyDaos.timNhanVienTheoID(txtTimNhanVien.getText());

				if (dsNhanVien.size() > 0) {
					modelNhanVien.setRowCount(0);
					for (int i = 0; i < dsNhanVien.size(); i++) {
						String s[] = { dsNhanVien.get(i).getIDNhanVien(), dsNhanVien.get(i).getHo(),
								dsNhanVien.get(i).getTen(), dsNhanVien.get(i).getSDT(), dsNhanVien.get(i).getDiaChi(),
								dsNhanVien.get(i).getNgaySinh().toString() };
						modelNhanVien.addRow(s);
					}
					txtTimNhanVien.setText("");
				} else
					JOptionPane.showMessageDialog(null, "Không tìm thấy", "", JOptionPane.INFORMATION_MESSAGE);
				dsNhanVien = quanLyDaos.getAllNhanVien();
			} else if (cbbTimNhanVien.getSelectedIndex() == 1 && !txtTimNhanVien.getText().isEmpty()) {
				dsNhanVien = quanLyDaos.timNhanVienTheoTen(txtTimNhanVien.getText());

				if (dsNhanVien.size() > 0) {
					modelNhanVien.setRowCount(0);
					for (int i = 0; i < dsNhanVien.size(); i++) {
						String s[] = { dsNhanVien.get(i).getIDNhanVien(), dsNhanVien.get(i).getHo(),
								dsNhanVien.get(i).getTen(), dsNhanVien.get(i).getSDT(), dsNhanVien.get(i).getDiaChi(),
								dsNhanVien.get(i).getNgaySinh().toString() };
						modelNhanVien.addRow(s);
					}
					txtTimNhanVien.setText("");
				} else
					JOptionPane.showMessageDialog(null, "Không tìm thấy", "", JOptionPane.INFORMATION_MESSAGE);
				dsNhanVien = quanLyDaos.getAllNhanVien();
			} else if (cbbTimNhanVien.getSelectedIndex() == 2 && !txtTimNhanVien.getText().isEmpty()) {
				dsNhanVien = quanLyDaos.timNhanVienTheoSDT(txtTimNhanVien.getText());

				if (dsNhanVien.size() > 0) {
					modelNhanVien.setRowCount(0);
					for (int i = 0; i < dsNhanVien.size(); i++) {
						String s[] = { dsNhanVien.get(i).getIDNhanVien(), dsNhanVien.get(i).getHo(),
								dsNhanVien.get(i).getTen(), dsNhanVien.get(i).getSDT(), dsNhanVien.get(i).getDiaChi(),
								dsNhanVien.get(i).getNgaySinh().toString() };
						modelNhanVien.addRow(s);
					}
					txtTimNhanVien.setText("");
				} else
					JOptionPane.showMessageDialog(null, "Không tìm thấy", "", JOptionPane.INFORMATION_MESSAGE);
				dsNhanVien = quanLyDaos.getAllNhanVien();
			}
		} else if (object.equals(btnXoaBenhNhan)) {
			int currentYear = LocalDate.now().getYear(), count = 0;
			List<BenhAn> benhAns = quanLyDaos.getAllBenhAn();

			for (int i = 0; i < benhAns.size(); i++) {
				int year = quanLyDaos.getNamLapBenhAn(benhAns, i);
				if (currentYear - year >= 2) {
					quanLyDaos.xoaBenhNhanTheoID(benhAns.get(i).getBenhNhan().getIDBenhNhan());
					count++;
				}
			}
			if (count == 0) {
				JOptionPane.showMessageDialog(null, "Không có bệnh nhân bị xóa", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Đã xóa " + count + " bệnh nhân", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (object.equals(btnDong)) {
			this.dispose();
			new Gui_DangNhap();
		} else if (object.equals(btnDoi)) {
			DoiMatKhau();
		} else if (object.equals(btnHuy)) {
			XoaTrangDoiMatKhau();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object object = e.getSource();

		if (object.equals(tableToaThuoc)) {
			int row = tableToaThuoc.getSelectedRow();
			loadTextField(ToaThuoc.class);

			modelChiTietToa.setRowCount(0);
			List<Thuoc> thuocs = new ArrayList<Thuoc>();
			thuocs = quanLyDaos.getThuocTheoToa(tableToaThuoc.getValueAt(row, 0).toString());

			for (int i = 0; i < thuocs.size(); i++) {
				int soLuong = quanLyDaos.getSoLuongThuoc(tableToaThuoc.getValueAt(row, 0).toString(),
						thuocs.get(i).getIDThuoc());
				double tongGia = 0;
				tongGia = soLuong * thuocs.get(i).getDonGia();

				String s[] = { thuocs.get(i).getIDThuoc(), thuocs.get(i).getTenThuoc(),
						Double.toString(thuocs.get(i).getDonGia()), Integer.toString(soLuong),
						Double.toString(tongGia) };
				modelChiTietToa.addRow(s);
			}
		} else if (object.equals(tableBenhNhan)) {
			loadTextField(BenhNhan.class);
		} else if (object.equals(tableNhanVien) && tableNhanVien.isEnabled()) {
			loadTextField(NhanVien.class);
		} else if (object.equals(tableThuoc)) {
			loadTextField(Thuoc.class);
		} else if (object.equals(tableTaiKhoan) && tableTaiKhoan.isEnabled()) {
			loadTextField(TaiKhoan.class);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
