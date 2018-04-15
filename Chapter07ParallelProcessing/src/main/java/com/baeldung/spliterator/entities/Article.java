package com.baeldung.spliterator.entities;

import java.util.List;

public class Article {

	private List<Author> listOfAuthors;
	private int id;
	private String name;
	
	public Article() {
	}

	public Article(List<Author> listOfAuthors, int id, String name) {
		super();
		this.listOfAuthors = listOfAuthors;
		this.id = id;
		this.name = name;
	}

	public List<Author> getListOfAuthors() {
		return listOfAuthors;
	}

	public void setListOfAuthors(List<Author> listOfAuthors) {
		this.listOfAuthors = listOfAuthors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((listOfAuthors == null) ? 0 : listOfAuthors.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Article other = (Article) obj;
		if (id != other.id)
			return false;
		if (listOfAuthors == null) {
			if (other.listOfAuthors != null)
				return false;
		} else if (!listOfAuthors.equals(other.listOfAuthors))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [listOfAuthors=" + listOfAuthors + ", id=" + id + ", name=" + name + "]";
	}

	
}
