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
                    <p class="card-text d-flex justify-content-between">Password: <input class="ml-2" type="password" id="password"></p>
                    <p class="card-text"><button class="btn btn-primary w-100 rounded-pill" type="button" id="btnEnter">Log in</button</p>
                  </div>
                  <p class="w-100 text-center">Don't have an account?<br><a id="registration" href="#">Registration</a></p>
               </div>
             </div>;`;
        document.getElementById('btnEnter').onclick = function () {
            authModule.auth();
        }
        document.getElementById('registration').addEventListener('click', userModule.printRegistrationForm);
    }
    auth() {
        console.log('отработал метода authModule.auth()');
    }
}
let authModule = new AuthModule();
export {authModule};

