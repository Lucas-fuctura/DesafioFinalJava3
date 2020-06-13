package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.MateriaDAO;
import dao.MateriaDAOImpl;
import entidade.Atividade;
import entidade.Estudante;
import entidade.Materia;

@ManagedBean(name="MateriaBean")
@SessionScoped
public class MateriaBean {
	private Materia materia;
	private Atividade AtvMateria;
	private MateriaDAO materiaDAO;
	private String AtvSelecionado;
	private List<Materia> listaMateria;
	private Long idMateria;
	private Estudante estudanteLogado;
	private String txtPesquisa;
	
	public MateriaBean() {
		this.iniciarInstancia();
		this.materiaDAO = new MateriaDAOImpl();
		this.listaMateria = new ArrayList<Materia>();
		
	}
	
	private void iniciarInstancia() {
		this.materia = new Materia();
		this.materia.setAtividades(new ArrayList<Atividade>());
		this.AtvMateria = new Atividade();
		this.atualizarEstudanteLogado();
	}
	
	public void atualizarEstudanteLogado() {
		HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		this.estudanteLogado = (Estudante)sessao.getAttribute("estudanteLogado");
	}
	
	public String pagPesquisa() {
		return "pagPesquisa.xhtml";
	}
	
	public String pagPrincipal() {
		return "pagPrincipal.xhtml";
	}
	
	public void salvarMateria() {

			if(this.materia.getId() == null) {
				this.materiaDAO.inserirMat(this.materia);	
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO, "Info!", "Nova Matéria Adcionada"));
			}else {
				this.materiaDAO.alterarMat(this.materia);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO, "Info!", "Matéria Atualizada"));
			}
		
	}
	
	public void adicionarAtv() {
		
		Atividade novo = new Atividade();
		
		novo.setMateria(this.materia);
		novo.setTipo(this.AtvMateria.getTipo());
		novo.setPontuacao(this.AtvMateria.getPontuacao());
		novo.setDetalhes(this.AtvMateria.getDetalhes());
		novo.setDataEntrega(this.AtvMateria.getDataEntrega());
		
		boolean achou = false;
		boolean vazio = false;
		
		for (Atividade atividade : this.materia.getAtividades()) {
			if(atividade.getDetalhes().equalsIgnoreCase(this.AtvMateria.getDetalhes())) {
				achou = true;
			}
		}
		
		if(this.AtvMateria.getTipo().isEmpty()) {
			vazio = true;
		}
		
		if(vazio) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR, "Erro!", "Preecha o Tipo da Atividade. Ex: Seminário"));
		} else {
		if(achou) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR, "Erro!", "Atividade Repetida"));
		}else {
			this.materia.getAtividades().add(novo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO, "Info!", "Nova Atividade Registrada"));
		}
		}
		
	}
	
	public void removerAtv() {
		Atividade atvEncontrado = null;
		
		for (Atividade atv : this.materia.getAtividades()) {
			if(atv.getTipo().equalsIgnoreCase(this.AtvSelecionado)) {
				atvEncontrado = atv;
			}
		}
		
		if(atvEncontrado != null) {
			this.materia.getAtividades().remove(atvEncontrado);
			if(atvEncontrado.getId() != null) {
				this.materiaDAO.removerAtv(atvEncontrado);
			}
		}
	}
	
	public void pesquisarMateria() {
		for(Materia materia: listaMateria) {
			if(materia.getNome().equals(this.txtPesquisa)) {
				this.listaMateria = this.materiaDAO.listarMaterias();
			}
		}
	}
	
public String editarMateria() {
		
		Materia materiaEncontrada = this.materiaDAO.pesquisarMat(this.idMateria);
		
		if(materiaEncontrada != null) {
			this.materia = materiaEncontrada;
			return "pagPrincipal.xhtml";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR, "Erro!", "Matéria Ainda não Registrada"));
			return "";
		}
	}
	
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public Atividade getAtvMateria() {
		return AtvMateria;
	}
	public void setAtvMateria(Atividade atvMateria) {
		AtvMateria = atvMateria;
	}
	public MateriaDAO getMateriaDAO() {
		return materiaDAO;
	}
	public void setMateriaDAO(MateriaDAO materiaDAO) {
		this.materiaDAO = materiaDAO;
	}
	public String getAtvSelecionado() {
		return AtvSelecionado;
	}
	public void setAtvSelecionado(String atvSelecionado) {
		AtvSelecionado = atvSelecionado;
	}
	public List<Materia> getListaMateria() {
		return listaMateria;
	}
	public void setListaMateria(List<Materia> listaMateria) {
		this.listaMateria = listaMateria;
	}
	public Long getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

	public Estudante getEstudanteLogado() {
		return estudanteLogado;
	}

	public void setEstudanteLogado(Estudante estudanteLogado) {
		this.estudanteLogado = estudanteLogado;
	}

	public String getTxtPesquisa() {
		return txtPesquisa;
	}

	public void setTxtPesquisa(String txtPesquisa) {
		this.txtPesquisa = txtPesquisa;
	}

}
