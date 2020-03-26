import {httpModule} from './HttpModule.js';
import {userModule} from './UserModule.js';

class AuthModule {

    printLoginForm() {
        document.getElementById('content').innerHTML =
                ` <div class="row mt-5 w-100 d-flex justify-content-center">
               <div class="card border-primary p-2" style="max-width: 30rem;">
                  <div class="card-header text-center">Enter login and password</div>
                  <div class="card-body">
                    <p class="card-text d-flex justify-content-between">Login: <input class="ml-2" type="text" id="login"></p>
                    <p class="card-text d-flex justify-content-between">Password: <input class="ml-2" type="text" id="password"></p>
                    <p class="card-text"><button class="btn btn-primary w-100 rounded-pill" type="button" id="btnEnter">Sign in</button</p>
                  </div>
                  <p class="w-100 text-center">Don't have an account?<br><a id="registration" href="#">Register</a></p>
               </div>
             </div>;`;
        document.getElementById('btnEnter').onclick = function () {
            authModule.auth();
        }
        document.getElementById('registration').addEventListener('click', userModule.printRegistrationForm);
    }
    auth() {
        let login = document.getElementById('login').value;
        let password = document.getElementById('password').value;
        let credential = {
            "login": login,
            "password": password,
        }
        httpModule.http('login', 'POST', credential)
                .then(function (response) {
                    if (response === null || response === undefined) {
                        document.getElementById('info').innerHTML = 'Authorization failed';
                        return;
                    }
                    if (response.authStatus == 'false') {
                        document.getElementById('info').innerHTML = 'Wrong login or password';
                        return;
                    }
                    document.getElementById('info').innerHTML = 'You are logged in as ' + response.user.login;
                    sessionStorage.setItem('user', JSON.stringify(response.user));
                });
    }

}
let authModule = new AuthModule();
export {authModule};