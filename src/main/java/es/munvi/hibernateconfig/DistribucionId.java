package es.munvi.hibernateconfig;
// Generated 13-sep-2015 13:03:19 by Hibernate Tools 4.3.1



/**
 * DistribucionId generated by hbm2java
 */
public class DistribucionId  implements java.io.Serializable {


     private String cifc;
     private String codcoche;

    public DistribucionId() {
    }

    public DistribucionId(String cifc, String codcoche) {
       this.cifc = cifc;
       this.codcoche = codcoche;
    }
   
    public String getCifc() {
        return this.cifc;
    }
    
    public void setCifc(String cifc) {
        this.cifc = cifc;
    }
    public String getCodcoche() {
        return this.codcoche;
    }
    
    public void setCodcoche(String codcoche) {
        this.codcoche = codcoche;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DistribucionId) ) return false;
		 DistribucionId castOther = ( DistribucionId ) other; 
         
		 return ( (this.getCifc()==castOther.getCifc()) || ( this.getCifc()!=null && castOther.getCifc()!=null && this.getCifc().equals(castOther.getCifc()) ) )
 && ( (this.getCodcoche()==castOther.getCodcoche()) || ( this.getCodcoche()!=null && castOther.getCodcoche()!=null && this.getCodcoche().equals(castOther.getCodcoche()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCifc() == null ? 0 : this.getCifc().hashCode() );
         result = 37 * result + ( getCodcoche() == null ? 0 : this.getCodcoche().hashCode() );
         return result;
   }   


}

