<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="favicon.ico">
        <title>Departamento de Recursos Humanos - My Company</title>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/justified-nav.css" rel="stylesheet">

    </head>

    <body>

        <div class="container">

            <!-- The justified navigation menu is meant for single line per list item.
                 Multiple lines will require custom code not provided by Bootstrap. -->
            <div class="masthead">
                <h3 class="text-muted">My Company</h3>
                <a href="frmvacante.jsp">FormVacantes</a>
                <p><a href="login.jsp">Login</a></p>
                <nav>
                    <ul class="nav nav-justified">
                        <li><a href="home">Inicio</a></li>            
                        <li><a href="login?accion=admin">Administración</a></li>                        
                        <li><a href="acerca.jsp">Acerca</a></li>            
                    </ul>
                </nav>
            </div>

            <!-- Formulario para la busqueda. El formulario es enviado por POST al BusquedaController -->    
            <form method ="post" action="busqueda" class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" name="query" required placeholder="Buscar oferta..." class="form-control">
                </div>        
                <button type="submit" class="btn btn-success">Buscar</button>
            </form>

            <!-- Jumbotron -->
            <div class="jumbotron">
                <h2>¡ENCUENTRA TU TRABAJO IDEAL!</h2>
                <!--
                <h4>ESTAMOS CONTRATANDO</h4>
                -->
                <p class="lead text-justify">Bienvenido a Sodom, aquí podrás encontrar ofertas de empleos 
                    que sean adecuados a tu perfil, experiencia y ubicación. 
                    Es muy fácil de usar, solo haz clic en una vacante, ingresa para ver los detalles y envíanos tu CV en formato 
                    PDF o DOCX. Nosotros revisaremos tu CV y posteriormente te contactaremos.<br><br>

                <p><a class="btn btn-lg btn-success" href="vacante?accion=getall" role="button">Ver más Ofertas</a></p>                
            </div>

            <h1>Ofertas recientes</h1>

            <!-- Example row of columns -->
            <div class="row">
                <c:forEach items="${vacantes}" var="objIndVac" varStatus="status">
                    <div class="col-lg-4">
                        <h2>${objIndVac.nombre}</h2>
                        <p class="text-danger text-justify">${objIndVac.descripcion}</p>
                        <p class="text-justify">${objIndVac.detalle}</p>
                        <p><a class="btn btn-primary" href="vacante?accion=ver&id=${objIndVac.id}" role="button">Ver Detalle &raquo;</a></p>
                    </div>
                </c:forEach>
               
                <!-- Site footer -->
                

            </div> <!-- /container -->
            <footer class="footer">
                    <p>&copy; 2020 Angel, Inc.</p>
            </footer>

    </body>
</html>
