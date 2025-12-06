# CIGuide - Wiki no oficial 

Una aplicación Android conectada a una arquitectura de microservicios en la nube para proveer información en tiempo real de Terraria (Calamity Mod).

## Arquitectura

El proyecto sigue una arquitectura Cliente-Servidor:

1.  **Base de Datos:** * **Tecnología:** Oracle Autonomous Database (Cloud).
    * **Función:** Almacena información relacional de Jefes e Items, incluyendo estructuras JSON para listas.
    * **Seguridad:** Conexión mediante Wallet.

2.  **Backend:**
    * **Tecnología:** Python + FastAPI + Uvicorn.
    * **Driver:** python-oracledb.
    * **Función:** Expone una API RESTful que consulta la DB, procesa los JSON y sirve los datos en formato estándar al cliente.

3.  **Frontend:**
    * **Tecnología:** Android (Kotlin).
    * **UI:** Jetpack Compose (Diseño Declarativo).
    * **Networking:** Retrofit2 + Gson (Consumo de API).
    * **Media:** Coil (Carga asíncrona de imágenes).

## Tecnologías Utilizadas

* **Lenguajes:** Kotlin, Python, SQL.
* **Frameworks:** FastAPI, Jetpack Compose.
* **Cloud:** Oracle Cloud Infrastructure.
* **Herramientas:** Android Studio, VS Code, Git.

## Instalación y Ejecución

### Backend (Python)
1. Clonar el repositorio.
2. Colocar la carpeta `wallet` de Oracle en la raíz (no se incluye por seguridad).
3. Ejecutar:
   ```bash
   pip install fastapi uvicorn oracledb
   python -m uvicorn main:app --reload
