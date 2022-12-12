function validar(){

	  var none=document.getElementById("none").value;
	  var correo=document.getElementById("correo").value;
    var Mensaje=document.getElementById("Mensaje").value;


	 if (none.length<1) {
  alert("ingrese contraseña");

    return false;
   }


  if (correo.length<1) {


    return false;
   }
 else {
   if (Mensaje.length<1) {
    alert("ingrese contraseña");

    return false;

   }
 }
  