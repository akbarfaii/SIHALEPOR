package com.example.halepor.model;

public class Datapratul {

    private String petugas, idpel_pratul, norbm_pratul,
            namapel_pratul, alamat_pratul, trfdaya_pratul, rptag_pratul,
            status_pratul, nohp_pratul;
    private int foto_pratul;
//    private String detail;

    public Datapratul(String petugas, String idpel_pratul, String norbm_pratul, String namapel_pratul,
                      String alamat_pratul, String Trfdaya_pratul, String rptag_pratul){

        this.petugas = petugas;
        this.idpel_pratul = idpel_pratul;
        this.norbm_pratul = norbm_pratul;
        this.namapel_pratul = namapel_pratul;
        this.alamat_pratul = alamat_pratul;
        this.trfdaya_pratul = Trfdaya_pratul;
        this.rptag_pratul = rptag_pratul;

    }

    public String getPetugas() {
        return petugas;
    }

    public void setPetugas(String petugas) {
        this.petugas = petugas;
    }

    public String getIdpel_pratul() {
        return idpel_pratul;
    }

    public void setIdpel_pratul(String idpel_pratul) {
        this.idpel_pratul = idpel_pratul;
    }

    public String getNorbm_pratul() {
        return norbm_pratul;
    }

    public void setNorbm_pratul(String norbm_pratul) {
        this.norbm_pratul = norbm_pratul;
    }

    public String getNamapel_pratul() {
        return namapel_pratul;
    }

    public void setNamapel_pratul(String namapel_pratul) {
        this.namapel_pratul = namapel_pratul;
    }

    public String getAlamat_pratul() {
        return alamat_pratul;
    }

    public void setAlamat_pratul(String alamat_pratul) {
        this.alamat_pratul = alamat_pratul;
    }

    public String getTrfdaya_pratul() {
        return trfdaya_pratul;
    }

    public void setTrfdaya_pratul(String trfdaya_pratul) {
        this.trfdaya_pratul = trfdaya_pratul;
    }

    public String getRptag_pratul() {
        return rptag_pratul;
    }

    public void setRptag_pratul(String rptag_pratul) {
        this.rptag_pratul = rptag_pratul;
    }
//    protected Datapratul(Parcel in) {
//        petugas = in.readString();
//        idpel_pratul = in.readString();
//        norbm_pratul = in.readString();
//        namapel_pratul = in.readString();
//        alamat_pratul = in.readString();
//        trfdaya_pratul = in.readString();
//        rptag_pratul = in.readString();
//    }

}