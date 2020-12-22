package br.com.springbatch.motor.dominio;

public class UserOdds {
    private int id;
    private int idUsuario;
    private int idCamapanha;
    private int idTransacao;
    private int idAuditorLogs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCamapanha() {
        return idCamapanha;
    }

    public void setIdCamapanha(int idCamapanha) {
        this.idCamapanha = idCamapanha;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public int getIdAuditorLogs() {
        return idAuditorLogs;
    }

    public void setIdAuditorLogs(int idAuditorLogs) {
        this.idAuditorLogs = idAuditorLogs;
    }

    @Override
    public String toString() {
        return "UserOdds{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idCamapanha=" + idCamapanha +
                ", idTransacao=" + idTransacao +
                ", idAuditorLogs=" + idTransacao +
                '}';
    }
}
