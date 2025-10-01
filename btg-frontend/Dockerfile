# --- Fase 1: Build ---
# Usamos una imagen oficial de Node.js (versión 20) para construir el proyecto
FROM node:20 AS build

# Establecemos el directorio de trabajo
WORKDIR /usr/src/app

# Copiamos package.json y package-lock.json para instalar las dependencias
COPY package*.json ./
RUN npm install

# Copiamos el resto del código fuente del frontend
COPY . .

# Ejecutamos el comando de build de Angular para generar los archivos estáticos
RUN npm run build

# --- Fase 2: Serve ---
# Usamos una imagen de Nginx muy ligera para servir los archivos estáticos
FROM nginx:alpine

# Copiamos los archivos compilados desde la fase de 'build' al directorio de Nginx
COPY --from=build /usr/src/app/dist/btg-frontend/browser /usr/share/nginx/html

# Exponemos el puerto 80, que es el puerto por defecto de Nginx
EXPOSE 80

# El comando por defecto de la imagen de Nginx ya se encarga de iniciar el servidor