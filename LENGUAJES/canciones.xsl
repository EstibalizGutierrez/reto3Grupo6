<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding="UTF-8" doctype-system="about:legacy-compat"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <title>Login</title>
                <script src="Login.js"></script>
            </head>
            <body>
                    <h2>Acceso al Sistema</h2>
                    
                    <form id="formularioLogin" onsubmit="validarLogin(event)">
                        <label for="usuario">Usuario:</label><br>
                        <input type="text" id="usuario"></input>
                    </br>
                        
                        <label for="password">Contraseña:</label><br>
                        <input type="password" id="password" ></input>
                    </br>
    
                        <button type="submit">Entrar</button>
                    </form>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>