function validar(){

	 var correo=document.getElementById("correo").value;
	 var password=document.getElementById("password").value;


	 if (correo.length<1) {
	 	Swal.fire({
  icon: '',
  title: 'Oops...',
  text: 'NO se a ingresado un correo!',
  footer: 'Vuelva a intentarlo'
     } )

	 	return false;
	 }
 else {
 	 if (password.length<1) {
	 	alert("ingrese contraseña");

	 	return false;
	 }
 }
  if(correo === "admi" && password === "jfrc"){
      Swal.fire(
  '',
  'Bienvenido adimistrador',
  'success'
)
  
 
  
  	window.location="profileAdmi.html";
}


else
 if(correo === "empleado" && password === "1234"){
  	Swal.fire(
  '',
  'Bienvenido empleado',
  'success'
)
  
  	window.location="profileEmpleado.html";
}

} 