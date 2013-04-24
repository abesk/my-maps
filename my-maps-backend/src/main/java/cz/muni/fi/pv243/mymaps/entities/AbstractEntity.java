package cz.muni.fi.pv243.mymaps.entities;


public abstract class AbstractEntity  {
    protected Long id;

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
