package com.example.practica.ui.view

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.practica.R
import com.example.practica.data.RetrofitInstance
import com.example.practica.data.service.ProfileDto
import kotlinx.coroutines.launch
import java.io.File

<<<<<<< HEAD
=======
/**
 * Экран профиля пользователя
 * Отображает информацию о пользователе, позволяет редактировать данные и загружать фото
 *
 * @param navController навигационный контроллер для переходов между экранами
 * @param userId уникальный идентификатор пользователя из Supabase auth.users
 * @param accessToken токен доступа для авторизации запросов к API
 */
>>>>>>> Day-3
@Composable
fun ProfileScreen(
    navController: NavHostController,
    userId: String,          // uuid из Supabase auth.users
    accessToken: String      // access_token из signIn/signUp
) {
<<<<<<< HEAD
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var isEditing by remember { mutableStateOf(false) }

=======
    // Контекст для доступа к системным сервисам и ресурсам
    val context = LocalContext.current
    // Корутин скоуп для асинхронных операций
    val scope = rememberCoroutineScope()

    // Состояние режима редактирования
    var isEditing by remember { mutableStateOf(false) }

    // Состояния для полей профиля
>>>>>>> Day-3
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var avatarUri by remember { mutableStateOf<Uri?>(null) }

<<<<<<< HEAD
    var isLoading by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf<String?>(null) }

    // ---------- камера ----------
    val tmpImageUri = remember {
        val file = File(context.cacheDir, "profile_photo.jpg")
        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) avatarUri = tmpImageUri
    }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) cameraLauncher.launch(tmpImageUri)
        else Toast.makeText(context, "Нужен доступ к камере", Toast.LENGTH_SHORT).show()
    }
=======
    // Состояния загрузки и ошибок
    var isLoading by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf<String?>(null) }

    // ---------- Камера и разрешения ----------
    /**
     * Создание временного URI для сохранения фото с камеры
     * Используется FileProvider для безопасного доступа к файлам
     */
    val tmpImageUri = remember {
        val file = File(context.cacheDir, "profile_photo.jpg") // Файл в кэше приложения
        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }

    /**
     * Лаунчер для камеры - запускает приложение камеры и получает результат
     */
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) avatarUri = tmpImageUri // Если фото сделано успешно, сохраняем URI
    }

    /**
     * Лаунчер для запроса разрешения на использование камеры
     */
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) cameraLauncher.launch(tmpImageUri) // Если разрешение получено, запускаем камеру
        else Toast.makeText(context, "Нужен доступ к камере", Toast.LENGTH_SHORT).show()
    }

    /**
     * Функция запуска камеры с проверкой разрешений
     */
>>>>>>> Day-3
    fun launchCamera() {
        val ok = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
        if (ok) cameraLauncher.launch(tmpImageUri) else permissionLauncher.launch(Manifest.permission.CAMERA)
    }

<<<<<<< HEAD
    // ---------- загрузка профиля ----------
=======
    // ---------- Загрузка профиля ----------
    /**
     * Загрузка данных профиля при первом входе на экран
     * Выполняется при изменении userId или accessToken
     */
>>>>>>> Day-3
    LaunchedEffect(userId, accessToken) {
        isLoading = true
        try {
            val service = RetrofitInstance.userManagementService
<<<<<<< HEAD
            val list: List<ProfileDto> = service.getProfile(
                authHeader = "Bearer $accessToken",
                userIdFilter = "eq.$userId"
            )
            val profile = list.firstOrNull()
            if (profile != null) {
=======
            // Запрос к API для получения профиля пользователя
            val list: List<ProfileDto> = service.getProfile(
                authHeader = "Bearer $accessToken",
                userIdFilter = "eq.${userId}" // Фильтр по ID пользователя
            )
            val profile = list.firstOrNull()
            if (profile != null) {
                // Заполняем поля данными из профиля
>>>>>>> Day-3
                firstName = profile.firstname.orEmpty()
                lastName = profile.lastname.orEmpty()
                address = profile.address.orEmpty()
                phone = profile.phone.orEmpty()
            } else {
                errorText = "Профиль не найден"
            }
        } catch (e: Exception) {
            errorText = "Не удалось загрузить профиль: ${e.localizedMessage}"
        } finally {
            isLoading = false
        }
    }

<<<<<<< HEAD
=======
    // Scaffold - базовая структура экрана с нижней навигацией
>>>>>>> Day-3
    Scaffold(
        containerColor = Color.White,
        bottomBar = { BottomBar(navController = navController, currentRoute = "profile") }
    ) { innerPadding ->
<<<<<<< HEAD
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

=======
        // Основной контейнер с учетом отступов от Scaffold
        Box(modifier = Modifier.padding(innerPadding)) {
            // Колонка с прокруткой для всего контента
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // Вертикальная прокрутка
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Верхний отступ
                Spacer(modifier = Modifier.height(16.dp))

                // Верхняя панель с заголовком и кнопкой редактирования
>>>>>>> Day-3
                TopHeader(isEditing = isEditing, onEditClick = { isEditing = !isEditing })

                Spacer(modifier = Modifier.height(24.dp))

<<<<<<< HEAD
                AvatarSection(
                    avatarUri = avatarUri,
                    onClick = { if (isEditing) launchCamera() }
=======
                // Секция аватара (фото профиля)
                AvatarSection(
                    avatarUri = avatarUri,
                    onClick = { if (isEditing) launchCamera() } // Камера доступна только в режиме редактирования
>>>>>>> Day-3
                )

                Spacer(modifier = Modifier.height(16.dp))

<<<<<<< HEAD
=======
                // Имя и фамилия пользователя
>>>>>>> Day-3
                Text(
                    text = "$firstName $lastName",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(24.dp))

<<<<<<< HEAD
=======
                // Карточка со штрих-кодом
>>>>>>> Day-3
                BarcodeCard()

                Spacer(modifier = Modifier.height(24.dp))

<<<<<<< HEAD
=======
                // Поля профиля (динамические - редактируемые или только для чтения)
>>>>>>> Day-3
                ProfileField("Имя", firstName, { firstName = it }, isEditing)
                ProfileField("Фамилия", lastName, { lastName = it }, isEditing)
                ProfileField("Адрес", address, { address = it }, isEditing)
                ProfileField("Телефон", phone, { phone = it }, isEditing)

<<<<<<< HEAD
=======
                // Кнопка сохранения (отображается только в режиме редактирования)
>>>>>>> Day-3
                if (isEditing) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
<<<<<<< HEAD
                            scope.launch {
                                isLoading = true
                                try {
=======
                            // Сохранение изменений профиля
                            scope.launch {
                                isLoading = true
                                try {
                                    // Подготовка данных для отправки
>>>>>>> Day-3
                                    val body = mapOf(
                                        "firstname" to firstName,
                                        "lastname" to lastName,
                                        "address" to address,
                                        "phone" to phone
                                    )
<<<<<<< HEAD
=======
                                    // Отправка запроса на обновление профиля
>>>>>>> Day-3
                                    val resp = RetrofitInstance.userManagementService.updateProfile(
                                        authHeader = "Bearer $accessToken",
                                        userIdFilter = "eq.$userId",
                                        body = body
                                    )
                                    if (resp.isSuccessful) {
<<<<<<< HEAD
                                        isEditing = false
=======
                                        isEditing = false // Выход из режима редактирования при успехе
>>>>>>> Day-3
                                    } else {
                                        errorText = "Ошибка сохранения: ${resp.code()}"
                                    }
                                } catch (e: Exception) {
                                    errorText = "Не удалось сохранить профиль: ${e.localizedMessage}"
                                } finally {
                                    isLoading = false
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF48B2E7))
                    ) {
                        Text("Сохранить", fontSize = 16.sp, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

<<<<<<< HEAD
                Spacer(modifier = Modifier.height(80.dp))
            }

=======
                // Нижний отступ для учета нижней навигации
                Spacer(modifier = Modifier.height(80.dp))
            }

            // Оверлей загрузки (полупрозрачный фон с индикатором)
>>>>>>> Day-3
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
<<<<<<< HEAD
                        .background(Color.Black.copy(alpha = 0.15f)),
=======
                        .background(Color.Black.copy(alpha = 0.15f)), // Полупрозрачный черный фон
>>>>>>> Day-3
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF48B2E7))
                }
            }
        }
    }

<<<<<<< HEAD
=======
    // Диалог ошибки (появляется при наличии errorText)
>>>>>>> Day-3
    if (errorText != null) {
        AlertDialog(
            onDismissRequest = { errorText = null },
            title = { Text("Ошибка") },
            text = { Text(errorText ?: "") },
            confirmButton = {
                TextButton(onClick = { errorText = null }) {
                    Text("OK")
                }
            }
        )
    }
}

<<<<<<< HEAD
// ---------- вспомогательные composable ----------

@Composable
fun TopHeader(isEditing: Boolean, onEditClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
=======
// ---------- Вспомогательные composable-функции ----------

/**
 * Верхняя панель с заголовком и кнопкой редактирования/готово
 * @param isEditing флаг режима редактирования
 * @param onEditClick колбэк при клике на кнопку
 */
@Composable
fun TopHeader(isEditing: Boolean, onEditClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // Заголовок по центру
>>>>>>> Day-3
        Text(
            text = "Профиль",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.align(Alignment.Center)
        )

<<<<<<< HEAD
=======
        // Кнопка справа
>>>>>>> Day-3
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(32.dp)
                .clip(CircleShape)
<<<<<<< HEAD
                .background(if (isEditing) Color.Transparent else Color(0xFF48B2E7))
=======
                .background(if (isEditing) Color.Transparent else Color(0xFF48B2E7)) // В режиме редактирования прозрачный фон
>>>>>>> Day-3
                .clickable { onEditClick() },
            contentAlignment = Alignment.Center
        ) {
            if (isEditing) {
<<<<<<< HEAD
                Text("Готово", fontSize = 12.sp, color = Color(0xFF48B2E7), fontWeight = FontWeight.Bold)
            } else {
=======
                // В режиме редактирования показываем текст "Готово"
                Text("Готово", fontSize = 12.sp, color = Color(0xFF48B2E7), fontWeight = FontWeight.Bold)
            } else {
                // В обычном режиме показываем иконку редактирования
>>>>>>> Day-3
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Edit",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

<<<<<<< HEAD
=======
/**
 * Секция аватара пользователя
 * @param avatarUri URI выбранного изображения (может быть null)
 * @param onClick колбэк при клике на аватар
 */
>>>>>>> Day-3
@Composable
fun AvatarSection(avatarUri: Uri?, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
<<<<<<< HEAD
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (avatarUri != null) {
            Image(
                painter = rememberAsyncImagePainter(model = avatarUri),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
=======
            .clip(CircleShape) // Круглая форма
            .background(Color.LightGray) // Серый фон-заглушка
            .clickable { onClick() }, // Кликабельно для выбора фото
        contentAlignment = Alignment.Center
    ) {
        if (avatarUri != null) {
            // Если есть выбранное фото, отображаем его
            Image(
                painter = rememberAsyncImagePainter(model = avatarUri), // Coil для загрузки изображения
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop, // Обрезка для заполнения круга
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // Если фото нет, показываем иконку-заглушку
>>>>>>> Day-3
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Placeholder",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

<<<<<<< HEAD
=======
/**
 * Карточка со штрих-кодом (стилизованный элемент интерфейса)
 */
>>>>>>> Day-3
@Composable
fun BarcodeCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
<<<<<<< HEAD
=======
        // Левая часть с вертикальным текстом "Открыть"
>>>>>>> Day-3
        Box(
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Открыть",
                fontSize = 12.sp,
                color = Color.Gray,
<<<<<<< HEAD
                modifier = Modifier.rotate(-90f),
=======
                modifier = Modifier.rotate(-90f), // Поворот текста на -90 градусов
>>>>>>> Day-3
                maxLines = 1
            )
        }

<<<<<<< HEAD
=======
        // Правая часть с изображением штрих-кода
>>>>>>> Day-3
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_barcode),
                contentDescription = "Barcode",
                modifier = Modifier.fillMaxWidth(),
<<<<<<< HEAD
                contentScale = ContentScale.FillBounds
=======
                contentScale = ContentScale.FillBounds // Растягивание для заполнения
>>>>>>> Day-3
            )
        }
    }
}

<<<<<<< HEAD
=======
/**
 * Поле профиля с заголовком и редактируемым текстом
 * @param title заголовок поля
 * @param value текущее значение
 * @param onValueChange колбэк при изменении значения
 * @param isEditing флаг режима редактирования (доступно только при true)
 */
>>>>>>> Day-3
@Composable
fun ProfileField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    isEditing: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
<<<<<<< HEAD
=======
        // Заголовок поля
>>>>>>> Day-3
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color(0xFF888888),
            modifier = Modifier.padding(bottom = 8.dp)
        )

<<<<<<< HEAD
        BasicTextField(
            value = value,
            onValueChange = { if (isEditing) onValueChange(it) },
            enabled = isEditing,
=======
        // Базовое текстовое поле (без Material оформления)
        BasicTextField(
            value = value,
            onValueChange = { if (isEditing) onValueChange(it) }, // Изменение только в режиме редактирования
            enabled = isEditing, // Поле активно только в режиме редактирования
>>>>>>> Day-3
            textStyle = LocalTextStyle.current.copy(
                color = Color.Black,
                fontSize = 16.sp
            ),
<<<<<<< HEAD
=======
            // Декорация поля (фон, отступы)
>>>>>>> Day-3
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .clip(RoundedCornerShape(12.dp))
<<<<<<< HEAD
                        .background(Color(0xFFF7F7F7))
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
=======
                        .background(Color(0xFFF7F7F7)) // Светло-серый фон
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField() // Содержимое поля
>>>>>>> Day-3
                }
            }
        )
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> Day-3
