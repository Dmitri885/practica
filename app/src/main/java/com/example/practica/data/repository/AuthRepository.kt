/*
 * Класс: AuthRepository
 * Назначение: работа с аутентификацией через Supabase
 * Дата создания: 02.03.2026
 * Автор: Dmitri885
 */

package com.example.practica.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {

    // Создание Supabase клиента
    private val supabase: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = "https://htjfzeboqyefvivffazp.supabase.co", // Замените на ваш URL
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imh0amZ6ZWJvcXllZnZpdmZmYXpwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjkwNzE4MDEsImV4cCI6MjA4NDY0NzgwMX0.9MdxrhdeECPNxAtl3sJjiJwS6w8KVdY87ZvIe5Cup4Y" // Замените на ваш ключ
        ) {
            install(Auth)
            install(Postgrest)
        }
    }

    // Метод регистрации - ИСПРАВЛЕННАЯ ВЕРСИЯ
    suspend fun register(email: String, password: String, name: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                // Регистрация через Supabase
                supabase.auth.signUpWith(Email) {
                    this.email = email
                    this.password = password
                }

                // Если исключение не было выброшено, регистрация успешна
                Result.success(Unit)

            } catch (e: Exception) {
                // В случае ошибки возвращаем failure
                Result.failure(e)
            }
        }
}