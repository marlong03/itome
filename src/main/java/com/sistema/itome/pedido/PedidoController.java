package com.sistema.itome.pedido;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lowagie.text.DocumentException;
import com.sistema.itome.fomaPago.FormaPagoRepository;
import com.sistema.itome.fomaPago.Formpago;
import com.sistema.itome.producto.Producto;
import com.sistema.itome.producto.ProductoRepository;
import com.sistema.itome.usuarios.Usuario;
import com.sistema.itome.usuarios.UsuarioRepository;






@Controller
public class PedidoController {


	@Autowired

	private PedidoRepository  pedidoRepository; 

	@Autowired
	private FormaPagoRepository formaPagoRepository;
	
	@Autowired
	private ProductoRepository producotRepository;
	
	@Autowired
	private UsuarioRepository Usrepository;
	
	@Autowired
	private PedidoService pedidoservice;
	
	@GetMapping("pedidos/nuevo")
	public String mostrarFormularioDeNuevoPedido(Model modelo) {
		
		List<Usuario> listaUsuario = Usrepository.findAll();
		List<Formpago> listaFormpago= formaPagoRepository.findAll();
		List<Producto> listaProducto= producotRepository.findAll();
		modelo.addAttribute("listaFormpago", listaFormpago);
		modelo.addAttribute("listaProducto", listaProducto);
		
		modelo.addAttribute("pedidos", new Pedido());

		modelo.addAttribute("listaUsuario", listaUsuario);
		return "pedidos_form";
	}
	
	
	@GetMapping("/pedidos")
	public String listaPedido(Model modelo,@Param("comprasClave")String pedidoClave) {
		List<Pedido> listaPedido = pedidoservice.listAll(pedidoClave);
		modelo.addAttribute("listaPedido", listaPedido);
		modelo.addAttribute("pedidoClave;",pedidoClave);
		return "pedidos";
	}
	@PostMapping("/pedidos/guardar")
	public String guardarPedido(Pedido pedidos, Usuario usuario) {

	
		
		
		pedidoRepository.save(pedidos);
		return "redirect:/pedidos";
	}

	
	@GetMapping("/pedidos/editar/{idpedidos}")
	public String mostrarFormularioDeModificarPedido(@PathVariable("idpedidos") Integer id,Model modelo) {
	Pedido pedido= pedidoRepository.findById(id).get();
	modelo.addAttribute("pedidos", pedido);

	List<Usuario> listaUsuario = Usrepository.findAll();
	List<Formpago> listaFormpago= formaPagoRepository.findAll();
	List<Producto> listaProducto= producotRepository.findAll();
	modelo.addAttribute("listaFormpago", listaFormpago);
	modelo.addAttribute("listaProducto", listaProducto);
	modelo.addAttribute("listaUsuario", listaUsuario);

	return "pedidos_form";
	}


	@GetMapping("/pedidos/eliminar/{idpedidos}")
	public String eliminarPedido(@PathVariable("idpedidos")Integer id, Model modelo) {
		pedidoRepository.deleteById(id);
		return "redirect:/pedidos";
		
	}
	@GetMapping("/PedidoExporterExcel")
	public void exportarListadoPedidoEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Pedidos_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Pedido> pedido = pedidoservice.getAllUser();
		
		PedidoExporterExcel exporter = new PedidoExporterExcel(pedido);
		exporter.exportar(response);
	}
	
	@GetMapping("/pedidoExporterPdf")
	public void pedidoExporterPdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Pedidos_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<Pedido> pedido = pedidoservice.getAllUser();
		
		pedidoExporterPdf exporter = new pedidoExporterPdf(pedido);
		exporter.exportar(response);
	}
	
	
	

	
}
