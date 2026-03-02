/*
 * Класс: RegisterAccountScreen
 * Назначение: экран регистрации нового аккаунта
 * Дата создания: 02.03.2026
 * Автор: Dmitri885
 */

package com.example.practica.presentation.screens.register

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica.R
import kotlinx.coroutines.launch
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterAccountScreen(
    viewModel: RegisterViewModel = viewModel(),
    onNavigateToSignIn: () -> Unit = {}
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Состояния для полей ввода
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    // Состояние для отображения пароля
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    // Состояние для чекбокса согласия
    var isAgreed by rememberSaveable { mutableStateOf(false) }

    // Состояние для диалога ошибки
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Состояние загрузки
    var isLoading by remember { mutableStateOf(false) }

    // Наблюдаем за состоянием регистрации
    val registrationState by viewModel.registrationState.collectAsState()

    // Обработка состояния регистрации
    LaunchedEffect(registrationState) {
        when (registrationState) {
            is RegistrationState.Loading -> {
                isLoading = true
            }
            is RegistrationState.Success -> {
                isLoading = false
                Toast.makeText(context, "Регистрация успешна!", Toast.LENGTH_SHORT).show()
                onNavigateToSignIn()
            }
            is RegistrationState.Error -> {
                isLoading = false
                errorMessage = (registrationState as RegistrationState.Error).message
                showErrorDialog = true
            }
            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Заголовок
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(R.string.register_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1D2E)
            )

            Text(
                text = stringResource(R.string.register_subtitle),
                fontSize = 14.sp,
                color = Color(0xFF7C7C7C),
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Поле "Ваше имя"
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text(stringResource(R.string.name_label)) },
                placeholder = { Text(stringResource(R.string.name_placeholder)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        tint = Color(0xFF4B7FFB)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                isError = fullName.isNotEmpty() && fullName.length < 2,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF4B7FFB),
                    unfocusedBorderColor = Color(0xFFE8E8E8),
                    focusedLabelColor = Color(0xFF4B7FFB),
                    cursorColor = Color(0xFF4B7FFB)
                )
            )

            if (fullName.isNotEmpty() && fullName.length < 2) {
                Text(
                    text = "Имя должно содержать минимум 2 символа",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Поле "Email"
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.email_label)) },
                placeholder = { Text(stringResource(R.string.email_placeholder)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null,
                        tint = Color(0xFF4B7FFB)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = email.isNotEmpty() && !isValidEmail(email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF4B7FFB),
                    unfocusedBorderColor = Color(0xFFE8E8E8),
                    focusedLabelColor = Color(0xFF4B7FFB),
                    cursorColor = Color(0xFF4B7FFB)
                )
            )

            if (email.isNotEmpty() && !isValidEmail(email)) {
                Text(
                    text = "Некорректный формат email",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Поле "Пароль" - УПРОЩЕННАЯ ВЕРСИЯ БЕЗ ИКОНОК VISIBILITY
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.password_label)) },
                placeholder = { Text(stringResource(R.string.password_placeholder)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color(0xFF4B7FFB)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = password.isNotEmpty() && password.length < 6,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF4B7FFB),
                    unfocusedBorderColor = Color(0xFFE8E8E8),
                    focusedLabelColor = Color(0xFF4B7FFB),
                    cursorColor = Color(0xFF4B7FFB)
                )
            )

            if (password.isNotEmpty() && password.length < 6) {
                Text(
                    text = "Пароль должен содержать минимум 6 символов",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Чекбокс согласия на обработку персональных данных
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isAgreed = !isAgreed },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isAgreed,
                    onCheckedChange = { isAgreed = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF4B7FFB)
                    )
                )
                Text(
                    text = stringResource(R.string.consent_text),
                    fontSize = 14.sp,
                    color = Color(0xFF7C7C7C),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Кнопка регистрации
            Button(
                onClick = {
                    scope.launch {
                        viewModel.register(fullName, email, password)
                    }
                },
                enabled = isAgreed &&
                        fullName.length >= 2 &&
                        isValidEmail(email) &&
                        password.length >= 6,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4B7FFB),
                    disabledContainerColor = Color(0xFFB8C8FF)
                )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = stringResource(R.string.register_button),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Ссылка на страницу входа
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.already_have_account),
                    color = Color(0xFF7C7C7C)
                )
                Text(
                    text = stringResource(R.string.sign_in),
                    color = Color(0xFF4B7FFB),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { onNavigateToSignIn() }
                        .padding(start = 4.dp)
                )
            }
        }

        // Диалог ошибки
        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = { showErrorDialog = false },
                title = {
                    Text(text = "Ошибка")
                },
                text = {
                    Text(text = errorMessage)
                },
                confirmButton = {
                    TextButton(
                        onClick = { showErrorDialog = false }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

// Функция валидации email
fun isValidEmail(email: String): Boolean {
    val emailPattern = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$"
    return email.matches(Regex(emailPattern))
}