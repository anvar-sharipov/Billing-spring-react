import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/api": { // 	Все запросы, начинающиеся с /api, будут проксироваться.
        target: "http://localhost:8080", // твой Spring Boot сервер
        changeOrigin: true,
        secure: false,
      },
    },
  },
})
