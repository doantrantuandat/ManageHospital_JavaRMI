package htqlbv_entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Thuoc {
	private double DonGia;
	@Id
	private String IDThuoc;
	@Column(columnDefinition="nvarchar(550)")
	private String TenThuoc;
	private int SoLuongTon;
	
	@OneToMany(mappedBy = "IDThuoc",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ChiTietKhoThuoc> chiTietKhoThuocs;
	public Thuoc() {
		super();
	}
	public Thuoc(double donGia, String iDThuoc, String tenThuoc, int soLuongTon) {
		super();
		DonGia = donGia;
		IDThuoc = iDThuoc;
		TenThuoc = tenThuoc;
		SoLuongTon = soLuongTon;
	}
	public double getDonGia() {
		return DonGia;
	}
	public void setDonGia(double donGia) {
		DonGia = donGia;
	}
	public String getIDThuoc() {
		return IDThuoc;
	}
	public void setIDThuoc(String iDThuoc) {
		IDThuoc = iDThuoc;
	}
	public String getTenThuoc() {
		return TenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		TenThuoc = tenThuoc;
	}
	public int getSoLuongTon() {
		return SoLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		SoLuongTon = soLuongTon;
	}
	
	public List<ChiTietKhoThuoc> getChiTietKhoThuocs() {
		return chiTietKhoThuocs;
	}
	public void setChiTietKhoThuocs(List<ChiTietKhoThuoc> chiTietKhoThuocs) {
		this.chiTietKhoThuocs = chiTietKhoThuocs;
	}
	
	@Override
	public String toString() {
		return "Thuoc [DonGia=" + DonGia + ", IDThuoc=" + IDThuoc + ", TenThuoc=" + TenThuoc + ", SoLuongTon="
				+ SoLuongTon + "]";
	}
	
	
}
