/**
 * Lista de compras
 */
package com.markinster.listadecompras.models;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "shop_list")
@NamedQueries({
    @NamedQuery(name = "ShopList.findAll", query = "SELECT s FROM ShopList s Order by s.id desc"),
    @NamedQuery(name = "ShopList.findById", query = "SELECT s FROM ShopList s WHERE s.id = :id"),
    @NamedQuery(name = "ShopList.findByName", query = "SELECT s FROM ShopList s WHERE s.name like :name")})
public class ShopList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", length = 255)
    private String name;
    @OneToMany(mappedBy = "shopList", fetch = FetchType.LAZY)
    private List<Product> items;

    public ShopList() {
    }

    public ShopList(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getItems() {
		return items;
	}
    
    public void setItems(List<Product> items) {
		this.items = items;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopList)) {
            return false;
        }
        ShopList other = (ShopList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id+"="+name;
    }

	public double getTotal() {
		if (items == null)
			return 0;
		
		double tot = 0;
		for (Product product : items) {
			tot += product.getPrice() * product.getQuant();
		}
		return tot;
	}
    
}
