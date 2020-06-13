package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.EstudanteDAO;
import dao.EstudanteDAOImpl;
import entidade.Estudante;

@ManagedBean(name="LoginBean")
@SessionScoped
public class LoginBean {
	private String txtMatricula;
	private String txtSenha;
	private Estudante estudante;
	private EstudanteDAO estudanteDAO;
	private List<Estudante> listaEstudante;
	
	public LoginBean() {
		this.estudante = new Estudante();
		this.listaEstudante = new ArrayList<Estudante>();
		this.estudanteDAO = new EstudanteDAOImpl();
	}
	
	public String entrar() {
		Estudante estudanteLogado = null;
		boolean vazio = false;
		
		this.listaEstudante = this.estudanteDAO.listarTodos();
		
		for(Estudante listaPesquisa: listaEstudante) {
			if(listaPesquisa.getMatricula().equals(this.txtMatricula) && listaPesquisa.getSenha().equals(this.txtSenha)) {
				estudanteLogado = listaPesquisa;
			}
		}
		
		if(this.txtMatricula.isEmpty() || this.txtSenha.isEmpty()) {
			vazio = true;
		}
		
		if(vazio) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR, "Erro!", "Preencha os Campos do Login!!!"));
			return "";
		} else {
			if(estudanteLogado != null) {
				HttpSession sessao =  (HttpSession)FacesContext.getCurrentInstance()
						.getExternalContext().getSession(true);
				sessao.setAttribute("estudanteLogado", estudanteLogado);
				return "pagPrincipal.xhtml?faces-redirect=true&amp;includeViewParams=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_ERROR, "Erro!", "Estudante invalido!!!"));
				return "";
			}
		}
	}
	
	public void salvar() {
		Estudante novo = new Estudante();
		novo.setMatricula(this.estudante.getMatricula());
		novo.setNome(this.estudante.getNome());
		novo.setTurno(this.estudante.getTurno());
		novo.setSenha(this.estudante.getSenha());
		boolean achou = false;
		boolean vazio = false;
		
		for(Estudante listaPesquisa: listaEstudante) {
			if(listaPesquisa.getMatricula().equals(this.estudante.getMatricula())) {
				achou = true;
			}
		}
		
		if(this.estudante.getMatricula().isEmpty() || this.estudante.getNome().isEmpty()
				|| this.estudante.getTurno().isEmpty() || this.estudante.getSenha().isEmpty()) {
			vazio = true;
		}
		
		if(vazio) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR, "Erro!", "Preencha os Campos de Cadastro!"));
		} else {
			if(achou) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_ERROR, "Erro!", "Estudante já Existe!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO, "Info!", "Cadastro Efetuado!"));
				this.estudanteDAO.inserir(novo);
				this.estudante = new Estudante();	
			}
		}
	}
	
	public String getTxtMatricula() {
		return txtMatricula;
	}
	public void setTxtMatricula(String txtMatricula) {
		this.txtMatricula = txtMatricula;
	}
	public String getTxtSenha() {
		return txtSenha;
	}
	public void setTxtSenha(String txtSenha) {
		this.txtSenha = txtSenha;
	}
	public Estudante getEstudante() {
		return estudante;
	}
	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}
	public EstudanteDAO getEstudanteDAO() {
		return estudanteDAO;
	}
	public void setEstudanteDAO(EstudanteDAO estudanteDAO) {
		this.estudanteDAO = estudanteDAO;
	}
	public List<Estudante> getListaEstudante() {
		return listaEstudante;
	}
	public void setListaEstudante(List<Estudante> listaEstudante) {
		this.listaEstudante = listaEstudante;
	}
	
	

}
