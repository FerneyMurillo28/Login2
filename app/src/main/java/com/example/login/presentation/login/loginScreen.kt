package com.example.login.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.navigation.AppScreens
import com.example.login.presentation.components.EventDialog
import com.example.login.presentation.components.RoundedButton
import com.example.login.presentation.components.TransparentTextField

@Composable
fun LoginScreen(
    navController: NavController,
    state:LoginState,
    onLogin:(user:String,pass:String)->Unit,
    onNavigateToRegister : ( )->Unit,
    onDismissDiolog:()->Unit
){
    val emailValue= rememberSaveable{ mutableStateOf("") }
    val passwordValue= rememberSaveable{mutableStateOf("")}
    var passwordVisibility by remember{mutableStateOf(false)}
    val focusManager= LocalFocusManager.current
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ){
        Image(
            modifier = Modifier
                .width(450.dp)
                .height(250.dp),
            painter = painterResource(id=R.drawable.michi),
            contentDescription = "Logo",
            contentScale = ContentScale.FillBounds
            )
        Box(
            modifier =Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            ConstraintLayout{
                val (surface,fab)=createRefs()
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .constrainAs(surface) {
                            bottom.linkTo(parent.bottom)
                        },
                    color = Color.Transparent,
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                ) {
                    //Imput y password
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    ) {
                        Text(text = "Bienvenido",
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Text(text = "Acceder a la cuenta",
                            style = MaterialTheme.typography.h5.copy(
                                color=MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TransparentTextField(
                                textFieldValue = emailValue,
                                textLabel = "Email",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(focusDirection = FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next
                            )
                            TransparentTextField(
                                textFieldValue = passwordValue,
                                textLabel = "Password",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                        onLogin(emailValue.value,passwordValue.value)
                                    }
                                ),
                                imeAction = ImeAction.Done,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                        passwordVisibility = !passwordVisibility
                                        }
                                    )
                                    {
                                       Icon(
                                           imageVector = if (passwordVisibility) {
                                               Icons.Default.Visibility
                                           }else{
                                               Icons.Default.VisibilityOff
                                           },
                                           contentDescription = "Toggle"
                                       ) //finIcon
                                    }//finOnclick
                                },//fintrailIcon
                            visualTransformation=if(passwordVisibility){
                                VisualTransformation.None
                                }else{
                                    PasswordVisualTransformation()
                                }
                            )//fintextfielContraseña
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "Olvido su contraseña?",
                                style=MaterialTheme.typography.body1,
                                textAlign = TextAlign.End
                            )
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                RoundedButton(
                                    text ="Login",
                                    displayProgressBar = state.displayProgressBar,
                                    onClick = {
                                        onLogin(emailValue.value,passwordValue.value)
                                    }//finOnclickRounded Button
                                )//Fin roundedButton
                                ClickableText(
                                    text = buildAnnotatedString {
                                        append("No tiene una cuenta activa?")
                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colors.primary,
                                                fontWeight = FontWeight.Bold
                                            )
                                        ){
                                            append("Sing Up")
                                        }//finwithStyle
                                    },//FIntext
                                    onClick = {
//                                        navController.navigate(route = AppScreens.RegistrationScreen.ruta)
                                        onNavigateToRegister()
                                    }
                                )//Fin clickeableText
                            }//fin columna botones de login
                        }//finColumn
                    }
                }//fin surface
                FloatingActionButton(
                    modifier = Modifier
                        .size(72.dp)
                        .constrainAs(fab) {
                            top.linkTo(surface.top, margin = (-36).dp)
                            end.linkTo(surface.end, margin = 20.dp)
                        },
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = {onNavigateToRegister()})
                {
                 Icon(
                     modifier = Modifier.size(72.dp),
                     imageVector = Icons.Default.ArrowForward,
                     contentDescription ="boton de avanzar",
                     tint = Color.White
                 )
                }//fin floatingbutton
            }//fin constraint
        }//fin box2
        if (state.errorMessages != null ){
            EventDialog(
                onDismiss = onDismissDiolog,
                errorMessage = state.errorMessages
            )
        }
    }//fin box
}//fin funcion