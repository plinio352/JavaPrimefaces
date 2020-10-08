package br.com.principal.BEAN;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.principal.DAO.FornecedorDao;
import br.com.principal.VO.Fornecedor;

@ManagedBean(name="forBean")
@SessionScoped
public class FornecedorBean {
	private Fornecedor f = new Fornecedor();
	private FornecedorDao dao = new FornecedorDao();
	private DataModel<Fornecedor> dmfor;
	private String retorno = "erro";
	private String msgErro;

	
	public String getMsgErro() {
		return msgErro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	public void novo(){
		f = new Fornecedor();
	}
	
	public void selecionaFor(){
		f = dmfor.getRowData();
	}
	
	public String update(){
		
		try {
			dao.update(f);
			this.retorno = "lista";
		} catch (Exception e) {
			this.msgErro = e.getMessage();			
		}
		
		return retorno;
		
	}

	public String delete(){
		this.selecionaFor();
		
		try {
			dao.delete(f);
			this.retorno = "lista";
		} catch (Exception e) {
			this.msgErro = e.getMessage();			
		}
		
		return retorno;
		
	}

	public String insert(){
		
		try {
			dao.salvar(f);
			this.retorno = "lista";
		} catch (Exception e) {
			this.msgErro = e.getMessage();			
		}
		
		return retorno;
		
	}

	public Fornecedor getF() {
		return f;
	}

	public void setF(Fornecedor f) {
		this.f = f;
	}

	public DataModel<Fornecedor> getDmfor() {
		
		try {
			List<Fornecedor> lst = dao.getLista();
			dmfor = new ListDataModel<Fornecedor>(lst);
			
		} catch (Exception e) {
			this.msgErro = e.getMessage();			
		}
		
		return dmfor;
	}

	public void setDmfor(DataModel<Fornecedor> dmfor) {
		this.dmfor = dmfor;
	}
	

}
