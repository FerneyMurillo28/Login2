package com.example.login.presentation.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login.navigation.AppScreens
import com.example.login.presentation.components.EventDialog
import com.example.login.presentation.components.RoundedButton
import com.example.login.presentation.components.SocialMediaButton
import com.example.login.presentation.components.TransparentTextField

@Composable
fun RegistrationScreen(
    navController: NavController,
    state:RegisterState,
    onRegister: ( name:String, email:String,telefo:String, pass:String,confiPass:String )->Unit,
    onBack:()->Unit,
    onDismissDialog:()->Unit
    ){
    val nameValue= remember { mutableStateOf("") }
    val emailValue= remember { mutableStateOf("") }
    val phoneValue= remember { mutableStateOf("") }
    val passValue= remember { mutableStateOf("") }
    val confirmPassValue= remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager= LocalFocusManager.current
    Box(modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onBack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="Regresar al login" )
                }//FinIconButton
                Text(text = "Crear una Cuenta", style = MaterialTheme.typography.h6.copy(
                    color=MaterialTheme.colors.primary
                ))
            }//FinRow
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TransparentTextField(
                    textFieldValue = nameValue,
                    textLabel = "Name",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )//fintransparentFieldNombre
                TransparentTextField(
                    textFieldValue = emailValue,
                    textLabel = "Email",
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )//fintransparentFieldEmail
                TransparentTextField(
                    textFieldValue = phoneValue,
                    textLabel = "Telefono",
                    keyboardType = KeyboardType.Phone,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )//fintransparentFieldPhone
                TransparentTextField(
                    textFieldValue = passValue,
                    textLabel = "Contraseña",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next,
                    trailingIcon = {
                        IconButton(
                            onClick ={
                                passwordVisibility =! passwordVisibility
                            } //finOnclick
                        ) {
                            Icon(
                                imageVector = if (passwordVisibility) {
                                    Icons.Default.Visibility
                                }else{
                                     Icons.Default.VisibilityOff
                                     },
                                contentDescription = "Togle"
                            )//FinIcon
                        }//FinIconButton
                    },//finTrailIcon
                visualTransformation = if (passwordVisibility){
                    VisualTransformation.None
                }else{
                    PasswordVisualTransformation()
                }
                )//fintransparentFieldPass
                TransparentTextField(
                    textFieldValue = confirmPassValue,
                    textLabel = "Confirm Pass",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onRegister(nameValue.value,
                                emailValue.value,
                                phoneValue.value,
                                passValue.value,
                                confirmPassValue.value)
                        }
                    ),
                    imeAction = ImeAction.Done,
                    trailingIcon = {
                        IconButton(
                            onClick ={
                                passwordVisibility =! passwordVisibility
                            } //finOnclick
                        ) {
                            Icon(
                                imageVector = if (passwordVisibility) {
                                    Icons.Default.Visibility
                                }else{
                                    Icons.Default.VisibilityOff
                                },
                                contentDescription = "Togle"
                            )//FinIcon
                        }//FinIconButton
                    },//finTrailIcon
                    visualTransformation = if (passwordVisibility){
                        VisualTransformation.None
                    }else{
                        PasswordVisualTransformation()
                    }
                )//fintransparentFieldNombre
                Spacer(modifier =Modifier.height(16.dp) )
                RoundedButton(
                    text ="Sing Up",
                    displayProgressBar = state.displayProgressBar,
                    onClick = {
                        onRegister(
                            nameValue.value,
                            emailValue.value,
                            phoneValue.value,
                            passValue.value,
                            confirmPassValue.value
                        )
                        navController.navigate(AppScreens.HomeScreen.createRoute(nameValue.value.toString()))
                    }
                )//FinRounderButton
                ClickableText(text = buildAnnotatedString {
                    append("Ya tienes una cuenta")
                    withStyle(
                        style= SpanStyle(
                            color=MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold
                        )
                    ){
                        append("Log In")
                    }
                },
                    onClick ={
                        onBack()
                    }
                )
            }//FinColum2
            Spacer(modifier = Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray)
                    Text(modifier = Modifier.padding(8.dp),
                        text = "OR",
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Black)
                    )
                    Divider(
                        modifier=Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray)
                }//finROw2
                Text(modifier = Modifier.padding(8.dp),
                    text = "Login With",
                    style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary),
                textAlign = TextAlign.Center
                )
            }//finColum3
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
                SocialMediaButton(
                    text = "Facebook",
                    onClick = { /*TODO*/ },
                    socialMediaColor = MaterialTheme.colors.secondary)
                SocialMediaButton(
                    text = "Google",
                    onClick = { /*TODO*/ },
                    socialMediaColor = MaterialTheme.colors.secondaryVariant)
            }
        }//FinColumn
        if (state.errorMessages!=null){
            EventDialog(errorMessage = state.errorMessages, onDismiss = onDismissDialog)
        }
    }//finBox
}//FinFuncion