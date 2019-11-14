package htqlbv_entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
public class BenhAn {
	@Id
	@GeneratedValue
	private int IdBenhAn;
	private LocalDate NgayLap;
	@Column(columnDefinition = "nvarchar(50)")
	private String TenBenh;
	
	@ManyToOne
	@JoinColumn (name = "IDNhanVien", referencedColumnName = "IDNhanVien")
	private NhanVien nhanVien;
	
	@ManyToOne
	@JoinColumn (name = "IDBenhNhan",referencedColumnName = "IDBenhNhan")
	private BenhNhan benhNhan;
	
	@OneToMany(mappedBy = "benhAn",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DonXetNghiem> donXetNghiems;
	
	@OneToOne (mappedBy = "benhAn",cascade=CascadeType.REMOVE,optional=true)
	@PrimaryKeyJoinColumn
	private ToaThuoc toaThuoc;

	public BenhAn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BenhAn(LocalDate ngayLap, String tenBenh) {
		super();
		NgayLap = ngayLap;
		TenBenh = tenBenh;
	}

	public int getIdBenhAn() {
		return IdBenhAn;
	}

	public void setIdBenhAn(int idBenhAn) {
		IdBenhAn = idBenhAn;
	}

	public LocalDate getNgayLap() {
		return NgayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		NgayLap = ngayLap;
	}

	public String getTenBenh() {
		return TenBenh;
	}

	public void setTenBenh(String tenBenh) {
		TenBenh = tenBenh;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public List<DonXetNghiem> getDonXetNghiems() {
		return donXetNghiems;
	}

	public void setDonXetNghiems(List<DonXetNghiem> donXetNghiems) {
		this.donXetNghiems = donXetNghiems;
	}

	public ToaThuoc getToaThuoc() {
		return toaThuoc;
	}

	public void setToaThuoc(ToaThuoc toaThuoc) {
		this.toaThuoc = toaThuoc;
	}

	@Override
	public String toString() {
		return "BenhAn [IdBenhAn=" + IdBenhAn + ", NgayLap=" + NgayLap + ", TenBenh=" + TenBenh + ", nhanVien="
				+ nhanVien + ", benhNhan=" + benhNhan + ", donXetNghiems=" + donXetNghiems + ", toaThuoc=" + toaThuoc
				+ "]";
	}
	
	

	           
	
}
