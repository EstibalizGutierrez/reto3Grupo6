<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Login Sputify</title>
                <link rel="stylesheet" href="styles.css"/>
                <script src="Login.js"></script>
            </head>
            <body>
                <section id="seccion-login">
                    <h2>Iniciar Sesión</h2>
                    <form onsubmit="validarLogin(event)">
                    <label>Usuario:</label>
                    <input type="text" id="usuario" required="required"/>
                    
                    <label>Contraseña:</label>
                    <input type="password" id="password" required="required"/>
                    
                    <button type="submit">Entrar</button>
                    </form>
                </section>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>