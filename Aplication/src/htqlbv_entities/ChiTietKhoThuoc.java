package htqlbv_entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass (ChiTietKhoThuoc_PK.class)
public class ChiTietKhoThuoc {
	@Id
	@ManyToOne
	@JoinColumn(name = "IDToaThuoc" ,referencedColumnName = "IDToaThuoc")
	private ToaThuoc IDToaThuoc;
	@Id
	@ManyToOne
	@JoinColumn(name = "IDThuoc", referencedColumnName = "IDThuoc")
	private Thuoc IDThuoc;
	
	private int SoLuong;

	public ChiTietKhoThuoc() {
		super();
	}

	public ChiTietKhoThuoc(ToaThuoc iDToaThuoc, Thuoc iDThuoc, int soLuong) {
		super();
		IDToaThuoc = iDToaThuoc;
		IDThuoc = iDThuoc;
		SoLuong = soLuong;
	}

	public ToaThuoc getIDToaThuoc() {
		return IDToaThuoc;
	}

	public void setIDToaThuoc(ToaThuoc iDToaThuoc) {
		IDToaThuoc = iDToaThuoc;
	}

	public Thuoc getIDThuoc() {
		return IDThuoc;
	}

	public void setIDThuoc(Thuoc iDThuoc) {
		IDThuoc = iDThuoc;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	@Override
	public String toString() {
		return "ChiTietKhoThuoc [IDToaThuoc=" + IDToaThuoc + ", IDThuoc=" + IDThuoc + ", SoLuong=" + SoLuong + "]";
	}
	
	

	
	
	
	
	
}
