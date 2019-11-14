package htqlbv_entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class NhanVien {
	@Id
	private String IDNhanVien;
	@Column(columnDefinition = "nvarchar(50)")
	private String Ten;
	@Column(columnDefinition = "nvarchar(50)")
	private String Ho;
	@Column(columnDefinition = "nvarchar(50)")
	private String DiaChi;
	private String SDT;
	private LocalDate NgaySinh;

	@OneToOne(cascade = CascadeType.REMOVE,optional = true, mappedBy = "nhanVien")
	@PrimaryKeyJoinColumn
	private TaiKhoan taiKhoan;

	@OneToMany(mappedBy = "nhanVien", cascade = CascadeType.REMOVE,orphanRemoval=true, fetch = FetchType.LAZY)
	private List<BenhAn> benhAn;

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String iDNhanVien, String ten, String ho, String diaChi, String sDT, LocalDate ngaySinh) {
		super();
		IDNhanVien = iDNhanVien;
		Ten = ten;
		Ho = ho;
		DiaChi = diaChi;
		SDT = sDT;
		NgaySinh = ngaySinh;
	}

	public String getIDNhanVien() {
		return IDNhanVien;
	}

	public void setIDNhanVien(String iDNhanVien) {
		IDNhanVien = iDNhanVien;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public String getHo() {
		return Ho;
	}

	public void setHo(String ho) {
		Ho = ho;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public LocalDate getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public List<BenhAn> getBenhAn() {
		return benhAn;
	}

	public void setBenhAn(List<BenhAn> benhAn) {
		this.benhAn = benhAn;
	}

	@Override
	public String toString() {
		return "NhanVien [IDNhanVien=" + IDNhanVien + ", Ten=" + Ten + ", Ho=" + Ho + ", DiaChi=" + DiaChi + ", SDT="
				+ SDT + ", NgaySinh=" + NgaySinh + "]";
	}

}
