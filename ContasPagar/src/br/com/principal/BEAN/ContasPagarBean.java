package br.com.principal.BEAN;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import br.com.principal.DAO.ContasPagarDao;
import br.com.principal.DAO.FornecedorDao;
import br.com.principal.VO.ContasPagar;
import br.com.principal.VO.Fornecedor;

@ManagedBean(name="CtPgBean")
@SessionScoped
public class ContasPagarBean {
	private ContasPagar cp = new ContasPagar();
	private ContasPagarDao dao = new ContasPagarDao();
	private FornecedorDao forDao = new FornecedorDao();
	private DataModel<ContasPagar> dmCp;
	private String retorno = "erro";
	private int idFornecedor;
	
	public void novo(){
		this.cp = new ContasPagar();
	}
	
	public void selecionaCP(){
		this.cp = dmCp.getRowData();
	}
	
	public String update(){
		try {
			this.cp.setFornecedor(forDao.getReg(idFornecedor));
			this.dao.update(cp);
			this.retorno = "listar";
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());			
		}
		return this.retorno;
	}
	
	public String delete(){
		try {
			this.selecionaCP();
			this.dao.delete(cp);
			this.retorno = "listar";
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return this.retorno;
	}

	public String inclui(){
		
		try {
			this.cp.setFornecedor(forDao.getReg(idFornecedor));			
			this.dao.salvar(cp);
			this.retorno = "listar";
			//throw new Exception("teste envio exception!");
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			//this.retorno = retorno +"?msg="+e.getMessage();
		}
		return this.retorno;
	}

	public ContasPagar getCp() {
		return cp;
	}

	public void setCp(ContasPagar cp) {
		this.cp = cp;
	}

	public DataModel<ContasPagar> getDmCp() {
		try {
			List<ContasPagar> lst = this.dao.getLista();
			this.dmCp = new ListDataModel<ContasPagar>(lst);
		} catch (Exception e) {
			System.out.println("ERRI: " + e.getMessage());
		}
		return dmCp;
	}

	public void setDmCp(DataModel<ContasPagar> dmCp) {
		this.dmCp = dmCp;
	}

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	
	public Collection<SelectItem> getCarregarFornecedores(){
		Collection<SelectItem> lst = new ArrayList<SelectItem>();
		lst.add(new SelectItem(-1, "-- SELECIONE --"));
		List<Fornecedor> listaFornecedor = this.forDao.getLista();
		
		for (int i = 0; i < listaFornecedor.size(); i++) {
			lst.add(new SelectItem(listaFornecedor.get(i).getId(), listaFornecedor.get(i).getNome()));
		}
		
		return lst;
	}

	

}
