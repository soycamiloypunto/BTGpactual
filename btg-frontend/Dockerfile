# --- Fase 1: Build ---
FROM node:20 AS build

WORKDIR /usr/src/app

COPY package*.json ./
RUN npm install

COPY . .

RUN npm run build

# --- Fase 2: Serve ---
FROM nginx:alpine

COPY --from=build /usr/src/app/dist/btg-frontend/browser /usr/share/nginx/html

EXPOSE 80

