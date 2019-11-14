package htqlbv_entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class ToaThuoc {

	@Id
	private String IDToaThuoc;
	
	private String GhiChu;
	private boolean TrangThai;
	
	@OneToOne
	@JoinColumn(name = "IdBenhAn", referencedColumnName = "IdBenhAn")
	private BenhAn benhAn;
	
	@OneToMany(mappedBy = "IDToaThuoc",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ChiTietKhoThuoc> chiTietKhoThuocs;

	public ToaThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ToaThuoc(String iDToaThuoc, String ghiChu, boolean trangThai) {
		super();
		IDToaThuoc = iDToaThuoc;
		GhiChu = ghiChu;
		TrangThai = trangThai;
	}

	public String getIDToaThuoc() {
		return IDToaThuoc;
	}

	public void setIDToaThuoc(String iDToaThuoc) {
		IDToaThuoc = iDToaThuoc;
	}

	public String getGhiChu() {
		return GhiChu;
	}

	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}

	public boolean isTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(boolean trangThai) {
		TrangThai = trangThai;
	}
	public BenhAn getBenhAn() {
		return benhAn;
	}

	public void setBenhAn(BenhAn benhAn) {
		this.benhAn = benhAn;
	}

	public List<ChiTietKhoThuoc> getChiTietKhoThuocs() {
		return chiTietKhoThuocs;
	}

	public void setChiTietKhoThuocs(List<ChiTietKhoThuoc> chiTietKhoThuocs) {
		this.chiTietKhoThuocs = chiTietKhoThuocs;
	}

	@Override
	public String toString() {
		return "ToaThuoc [IDToaThuoc=" + IDToaThuoc + ", GhiChu=" + GhiChu + ", TrangThai=" + TrangThai + ", benhAn="
				+ benhAn + ", chiTietKhoThuocs=" + chiTietKhoThuocs + "]";
	}
	
	
	
}
