package htqlbv_entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class ChiTietKhoThuoc_PK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String IDToaThuoc;
	private String IDThuoc;
	public ChiTietKhoThuoc_PK() {
		super();
	}
	public ChiTietKhoThuoc_PK(String iDToaThuoc, String iDThuoc) {
		super();
		IDToaThuoc = iDToaThuoc;
		IDThuoc = iDThuoc;
	}
	public String getIDToaThuoc() {
		return IDToaThuoc;
	}
	public void setIDToaThuoc(String iDToaThuoc) {
		IDToaThuoc = iDToaThuoc;
	}
	public String getIDThuoc() {
		return IDThuoc;
	}
	public void setIDThuoc(String iDThuoc) {
		IDThuoc = iDThuoc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDThuoc == null) ? 0 : IDThuoc.hashCode());
		result = prime * result + ((IDToaThuoc == null) ? 0 : IDToaThuoc.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietKhoThuoc_PK other = (ChiTietKhoThuoc_PK) obj;
		if (IDThuoc == null) {
			if (other.IDThuoc != null)
				return false;
		} else if (!IDThuoc.equals(other.IDThuoc))
			return false;
		if (IDToaThuoc == null) {
			if (other.IDToaThuoc != null)
				return false;
		} else if (!IDToaThuoc.equals(other.IDToaThuoc))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ChiTietKhoThuoc_PK [IDToaThuoc=" + IDToaThuoc + ", IDThuoc=" + IDThuoc + "]";
	}
	
	
	
	
}
