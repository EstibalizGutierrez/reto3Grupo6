<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Login - Spotify Intranet</title>
                <script src="Login.js"></script>
            </head>
            <body>
                <h2>Acceso Clientes</h2>
                Usuario: <input type="text" id="u"/><br/>
                Contraseña: <input type="password" id="p"/><br/>
                <button onclick="validar()">Entrar</button>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>