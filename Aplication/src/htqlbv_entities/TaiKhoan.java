package htqlbv_entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class TaiKhoan {
	@Id
	private String IDTaiKhoan;
	@Column(columnDefinition="nvarchar(50)" , nullable = false)
	private String Loai;
	private String Username;
	private String Matkhau;
	private LocalDate NgayBatDau;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "IDNhanVien",referencedColumnName = "IDNhanVien")
	private NhanVien nhanVien;

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String iDTaiKhoan, String loai, String username, String matkhau, LocalDate ngayBatDau) {
		super();
		IDTaiKhoan = iDTaiKhoan;
		Loai = loai;
		Username = username;
		Matkhau = matkhau;
		NgayBatDau = ngayBatDau;
	}

	public String getIDTaiKhoan() {
		return IDTaiKhoan;
	}

	public void setIDTaiKhoan(String iDTaiKhoan) {
		IDTaiKhoan = iDTaiKhoan;
	}

	public String getLoai() {
		return Loai;
	}

	public void setLoai(String loai) {
		Loai = loai;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getMatkhau() {
		return Matkhau;
	}

	public void setMatkhau(String matkhau) {
		Matkhau = matkhau;
	}

	public LocalDate getNgayBatDau() {
		return NgayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		NgayBatDau = ngayBatDau;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "TaiKhoan [IDTaiKhoan=" + IDTaiKhoan + ", Loai=" + Loai + ", Username=" + Username + ", Matkhau="
				+ Matkhau + ", NgayBatDau=" + NgayBatDau + ", nhanVien=" + nhanVien + "]";
	}
	
}

