package br.com.springbatch.motor.dominio;

import java.util.Date;

public class AuditorLogs {
    private int id;
    private int qtdRaspadinhas;
    private int idTransacao;
    private int idCampanha;
    private Date dtProcessado;
    private int idUsuario;
    private String status;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdRaspadinhas() {
        return qtdRaspadinhas;
    }

    public void setQtdRaspadinhas(int qtdRaspadinhas) {
        this.qtdRaspadinhas = qtdRaspadinhas;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public int getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(int idCampanha) {
        this.idCampanha = idCampanha;
    }

    public Date getDtProcessado() {
        return dtProcessado;
    }

    public void setDtProcessado(Date dtProcessado) {
        this.dtProcessado = dtProcessado;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AuditorLogs{" +
                "id=" + id +
                ", qtdRaspadinhas=" + qtdRaspadinhas +
                ", idTransacao=" + idTransacao +
                ", idCampanha=" + idCampanha +
                ", dtProcessamento=" + dtProcessado +
                ", idUsuario=" + idUsuario +
                ", status=" + status +
                '}';
    }
}
