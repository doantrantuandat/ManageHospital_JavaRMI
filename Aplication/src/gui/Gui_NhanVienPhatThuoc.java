package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.table.DefaultTableModel;

import daos.NhanVienPhatThuocDaos;
import htqlbv_entities.ChiTietKhoThuoc;
import htqlbv_entities.NhanVien;
import htqlbv_entities.ToaThuoc;

public class Gui_NhanVienPhatThuoc extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnDong;
	private NhanVien nhanVien;
	// Khai bao cho tab quan ly toa thuoc
	private JLabel lblToaThuocID, lblDonGia, lblGhiChu;
	private JTextField txtToaThuocID, txtDonGia, txtGhiChu;
	private JTable tableToaThuoc, tableChiTietToa;
	private DefaultTableModel modelToaThuoc, modelChiTietToa;
	private JButton btnPhat;

	// Khai bao quan ly ca nhan
	private JLabel lblCaNhanID, lblHoCaNhan, lblTenCaNhan, lblSDTCaNhan, lblNSCaNhan, lblDiaChiCaNhan, lblMatKhauCu,
			lblMatKhauMoi, lblNhapLaiMK;
	private JTextField txtCaNhanID, txtHoCaNhan, txtTenCaNhan, txtSDTCaNhan, txtNSCaNhan, txtDiaChiCaNhan;
	private JPasswordField pwfMatKhauCu, pwfMatKhauMoi, pwfNhapLaiMK;
	private JButton btnDoi, btnHuy;

	// Su kien
	private List<ToaThuoc> dstt;
	private NhanVienPhatThuocDaos nhanVienPhatThuocDaos;

	public Gui_NhanVienPhatThuoc(NhanVien nhanVien) {
		this.nhanVien= nhanVien;
		nhanVienPhatThuocDaos = new NhanVienPhatThuocDaos();
		setSize(900, 600);
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

		// ------------------------------------------------------------------------------
		JTabbedPane tab = new JTabbedPane();
		tab.setTabPlacement(JTabbedPane.LEFT);

		/*
		 * Giao dien quan ly toa thuoc
		 */
		Box bToaThuoc = Box.createVerticalBox();
		Box bTT1, bTT2, bTT3, BTT4;
		Box bTT1_1, bTT1_2, bTT1_3;

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
		bTT1_3.add(lblGhiChu = new JLabel("Ghi chú"));
		bTT1_3.add(Box.createHorizontalStrut(10));
		bTT1_3.add(txtGhiChu = new JTextField());

		lblGhiChu.setPreferredSize(lblToaThuocID.getPreferredSize());
		lblDonGia.setPreferredSize(lblToaThuocID.getPreferredSize());

		// ***********************
		bToaThuoc.add(BTT4 = Box.createHorizontalBox());
		BTT4.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		BTT4.add(Box.createHorizontalStrut(650));
		BTT4.add(btnPhat = new JButton("Phát Thuốc"));
		BTT4.setMaximumSize(getMaximumSize());
		btnPhat.setPreferredSize(getPreferredSize());
		// ***********************
		bToaThuoc.add(bTT2 = Box.createVerticalBox());
		bTT2.setBorder(BorderFactory.createTitledBorder("Chi tiết toa thuốc"));

		String[] headersCTTT = "Mã thuốc;Tên thuốc;Số lượng;Đơn giá".split(";");
		modelChiTietToa = new DefaultTableModel(headersCTTT, 0);
		JScrollPane scrollCTTT = new JScrollPane();
		scrollCTTT.setViewportView(tableChiTietToa = new JTable(modelChiTietToa));
		tableChiTietToa.setRowHeight(20);
		tableChiTietToa.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		bTT2.add(scrollCTTT);

		// ***********************
		bToaThuoc.add(bTT3 = Box.createVerticalBox());
		bTT3.setBorder(BorderFactory.createTitledBorder("Danh sách toa thuốc"));

		String[] headersTT = "Mã toa thuốc;Đơn giá;Ghi chú".split(";");
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
		bCN1.add(Box.createHorizontalStrut(400));

		bCaNhan.add(bCN2 = Box.createHorizontalBox());
		bCaNhan.add(Box.createVerticalStrut(10));
		bCN2.setMaximumSize(new Dimension(1000, 30));
		bCN2.add(Box.createHorizontalStrut(20));
		bCN2.add(lblHoCaNhan = new JLabel("Họ"));
		bCN2.add(Box.createHorizontalStrut(10));
		bCN2.add(txtHoCaNhan = new JTextField());
		bCN2.add(Box.createHorizontalStrut(400));

		bCaNhan.add(bCN3 = Box.createHorizontalBox());
		bCaNhan.add(Box.createVerticalStrut(10));
		bCN3.setMaximumSize(new Dimension(1000, 30));
		bCN3.add(Box.createHorizontalStrut(20));
		bCN3.add(lblTenCaNhan = new JLabel("Tên"));
		bCN3.add(Box.createHorizontalStrut(10));
		bCN3.add(txtTenCaNhan = new JTextField());
		bCN3.add(Box.createHorizontalStrut(400));

		bCaNhan.add(bCN4 = Box.createHorizontalBox());
		bCaNhan.add(Box.createVerticalStrut(10));
		bCN4.setMaximumSize(new Dimension(1000, 30));
		bCN4.add(Box.createHorizontalStrut(20));
		bCN4.add(lblSDTCaNhan = new JLabel("Số điện thoại"));
		bCN4.add(Box.createHorizontalStrut(10));
		bCN4.add(txtSDTCaNhan = new JTextField());
		bCN4.add(Box.createHorizontalStrut(400));

		bCaNhan.add(bCN5 = Box.createHorizontalBox());
		bCaNhan.add(Box.createVerticalStrut(10));
		bCN5.setMaximumSize(new Dimension(1000, 30));
		bCN5.add(Box.createHorizontalStrut(20));
		bCN5.add(lblDiaChiCaNhan = new JLabel("Địa chỉ"));
		bCN5.add(Box.createHorizontalStrut(10));
		bCN5.add(txtDiaChiCaNhan = new JTextField());
		bCN5.add(Box.createHorizontalStrut(400));

		bCaNhan.add(bCN6 = Box.createHorizontalBox());
		bCN6.setMaximumSize(new Dimension(1000, 30));
		bCN6.add(Box.createHorizontalStrut(20));
		bCN6.add(lblNSCaNhan = new JLabel("Ngày sinh"));
		bCN6.add(Box.createHorizontalStrut(10));
		bCN6.add(txtNSCaNhan = new JTextField());
		bCN6.add(Box.createHorizontalStrut(400));

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
		bMK1.add(Box.createHorizontalStrut(400));

		bMatKhau.add(bMK2 = Box.createHorizontalBox());
		bMatKhau.add(Box.createVerticalStrut(10));
		bMK2.setMaximumSize(new Dimension(1000, 30));
		bMK2.add(Box.createHorizontalStrut(20));
		bMK2.add(lblMatKhauMoi = new JLabel("Nhập mật mới"));
		bMK2.add(Box.createHorizontalStrut(10));
		bMK2.add(pwfMatKhauMoi = new JPasswordField());
		bMK2.add(Box.createHorizontalStrut(400));

		bMatKhau.add(bMK3 = Box.createHorizontalBox());
		bMatKhau.add(Box.createVerticalStrut(10));
		bMK3.setMaximumSize(new Dimension(1000, 30));
		bMK3.add(Box.createHorizontalStrut(20));
		bMK3.add(lblNhapLaiMK = new JLabel("Nhập lại mật khẩu"));
		bMK3.add(Box.createHorizontalStrut(10));
		bMK3.add(pwfNhapLaiMK = new JPasswordField());
		bMK3.add(Box.createHorizontalStrut(400));

		bMatKhau.add(bMK4 = Box.createHorizontalBox());
		bMatKhau.add(Box.createVerticalStrut(1000));
		bMK4.add(btnDoi = new JButton("Đổi"));
		bMK4.add(Box.createHorizontalStrut(30));
		bMK4.add(btnHuy = new JButton("Hủy"));
		bMK4.add(Box.createHorizontalStrut(10));
		bMK4.add(Box.createHorizontalStrut(430));

		lblMatKhauMoi.setPreferredSize(lblMatKhauCu.getPreferredSize());
		lblNhapLaiMK.setPreferredSize(lblMatKhauCu.getPreferredSize());

		tabCaNhan.addTab("Thông tin cá nhân", bCaNhan);
		tabCaNhan.addTab("Đổi mật khẩu", bMatKhau);

		/*
		 * Phan ket noi tab va gan su kien
		 */
		tab.addTab("Quản lý cá nhân", tabCaNhan);
		tab.addTab("Quản lý toa thuốc", bToaThuoc);

		///////////////////////////////////////////////////////
		tableToaThuoc.setDefaultEditor(Object.class, null);
		tableChiTietToa.setDefaultEditor(Object.class, null);
		tableChiTietToa.setEnabled(false);
		tableToaThuoc.setEnabled(false);

		txtToaThuocID.setEditable(false);
		txtDonGia.setEditable(false);
		txtGhiChu.setEditable(false);
		
		txtCaNhanID.setEditable(false);
		txtTenCaNhan.setEditable(false);
		txtHoCaNhan.setEditable(false);
		txtDiaChiCaNhan.setEditable(false);
		txtSDTCaNhan.setEditable(false);
		
		///////////////////////////////////////////////////////
		btnDong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPhat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PhatThuoc();
			}
		});
		btnDong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
		btnDoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DoiMatKhau();
				
			}
		});
		///////////////////////////////////////////////////////
		LoadDanhSachToaThuoc();
		LoadDanhSachChiTietThuoc();
		LoadThongTinCaNhan();
		///////////////////////////////////////////////////////
		add(tab, BorderLayout.CENTER);
		setVisible(true);
	}
	private void LoadDanhSachToaThuoc() {
		dstt = new ArrayList<>();
		dstt = nhanVienPhatThuocDaos.GetListToaThuocChuaPhat();
		if (!dstt.isEmpty()) {
			for (int i = 0; i < dstt.size(); i++) {
				String a[] = { dstt.get(i).getIDToaThuoc(),
						nhanVienPhatThuocDaos.TinhDonGiaToaThuoc(dstt.get(i).getIDToaThuoc()).toString(),
						dstt.get(i).getGhiChu() };
				modelToaThuoc.addRow(a);
			}
		}
	}

	private void LoadDanhSachChiTietThuoc() {
		if (tableToaThuoc.getRowCount() > 0) {
			tableToaThuoc.setRowSelectionInterval(0, 0);
			SetTextToaThuoc();
			int a = tableToaThuoc.getSelectedRow();
			String idtoa = modelToaThuoc.getValueAt(a, 0).toString();
			List<ChiTietKhoThuoc> dskt = new ArrayList<>();
			dskt = nhanVienPhatThuocDaos.getChiTietKhoThuoc(idtoa);
			if (!dskt.isEmpty()) {
				XoaBangChiTietToaThuoc();
				for (int i = 0; i < dskt.size(); i++) {
					String b[] = { dskt.get(i).getIDThuoc().getIDThuoc(), dskt.get(i).getIDThuoc().getTenThuoc(),
							Integer.toString(dskt.get(i).getSoLuong()),
							Double.toString(dskt.get(i).getIDThuoc().getDonGia()) };
					modelChiTietToa.addRow(b);
				}
			}
		}
	}
	private void XoaBangChiTietToaThuoc() {
		int a = modelChiTietToa.getRowCount();
		for (int i = a - 1; i >= 0; i--) {
			modelChiTietToa.removeRow(i);
		}
	}

	private void XoaBangToaThuoc() {
		int a = modelToaThuoc.getRowCount();
		for (int i = a - 1; i >= 0; i--) {
			modelToaThuoc.removeRow(i);
		}
	}

	private void SetTextToaThuoc() {
		txtToaThuocID.setText(tableToaThuoc.getValueAt(0, 0).toString());
		txtDonGia.setText(tableToaThuoc.getValueAt(0, 1).toString());
		txtGhiChu.setText(tableToaThuoc.getValueAt(0, 2).toString());
	}

	private void PhatThuoc() {
		dstt.get(0).setTrangThai(true);
		if (nhanVienPhatThuocDaos.CapNhatTrangThaiToaThuoc(dstt.get(0))) {
			XoaBangToaThuoc();
			XoaBangChiTietToaThuoc();
			LoadDanhSachToaThuoc();
			LoadDanhSachChiTietThuoc();
		}
	}
	private void LoadThongTinCaNhan() {
		txtCaNhanID.setText(nhanVien.getIDNhanVien());
		txtDiaChiCaNhan.setText(nhanVien.getDiaChi());
		txtHoCaNhan.setText(nhanVien.getHo());
		txtTenCaNhan.setText(nhanVien.getTen());
		txtSDTCaNhan.setText(nhanVien.getSDT());
		txtNSCaNhan.setText(nhanVien.getNgaySinh().toString());
	}
	private void XoaTrangDoiMatKhau()
	{
		pwfMatKhauCu.setText("");
		pwfMatKhauMoi.setText("");
		pwfNhapLaiMK.setText("");
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
		if(!nhanVienPhatThuocDaos.CheckMatKhauCu(nhanVien, mkc)) {
			System.out.println(mkc);
			JOptionPane.showMessageDialog(new JFrame(),"Mật khẩu cũ không khớp");
			XoaTrangDoiMatKhau();
			return false;
		}else if(!mkm.equals(nlmkm)) {
			JOptionPane.showMessageDialog(new JFrame(),"Nhập lại mật khẩu mới không khớp");
			XoaTrangDoiMatKhau();
			return false;
		}else if(!nhanVienPhatThuocDaos.DoiMatKhau(nhanVien, nlmkm)) {
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
