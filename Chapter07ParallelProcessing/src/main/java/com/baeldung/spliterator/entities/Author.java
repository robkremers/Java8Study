package com.baeldung.spliterator.entities;

public class Author {

	private String name;
	private int relatedArticleId;
	
	public Author() {
		// TODO Auto-generated constructor stub
	}

	public Author(String name, int relatedArticleId) {
		super();
		this.name = name;
		this.relatedArticleId = relatedArticleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRelatedArticleId() {
		return relatedArticleId;
	}

	public void setRelatedArticleId(int relatedArticleId) {
		this.relatedArticleId = relatedArticleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + relatedArticleId;
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
		Author other = (Author) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (relatedArticleId != other.relatedArticleId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + ", relatedArticleId=" + relatedArticleId + "]";
	}
	
}
