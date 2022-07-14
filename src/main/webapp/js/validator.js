//Registrazione
const nomeR = document.querySelector('#nomeId');
const cognomeR = document.querySelector('#cognomeId');
const emailR = document.querySelector('#emailIdRegistrati');
const passwordR = document.querySelector('#passwordIdRegistrati');
const passwordConferma = document.querySelector('#passwordConferma');
const telefonoR = document.querySelector('#telefonoId');
//Login
const emailA = document.querySelector('#emailIdAccedi');
const passwordA = document.querySelector('#passwordIdAccedi');
//Categoria
const nomeC = document.querySelector('#nomeIdInsert');
const priorityC = document.querySelector('#priorityIdInsert');


const formR = document.querySelector('#formRegistrati');
const formA = document.querySelector('#formAccedi');

function validateRegistration(){
    let isNomeValid = checkNome(nomeR),
        isCognomeValid = checkCognome(),
        isEmailValid = checkEmail(emailR),
        isPasswordValid = checkPassword(passwordR),
        isConfirmPasswordValid = checkConfirmPassword(),
        isTelefonoValid = checkTelefono();

    let isFormValid = isNomeValid &&
        isCognomeValid &&
        isEmailValid &&
        isPasswordValid &&
        isConfirmPasswordValid &&
        isTelefonoValid;

    return isFormValid;
}

function validateLogin(){
    let isEmailValid = checkEmail(emailA),
        isPasswordValid = checkPassword(passwordA);

    let isFormValid =
        isEmailValid &&
        isPasswordValid;

    return isFormValid;
}

function validateInsertCategoria(){
    let isNomeValid = checkNome(nomeC),
        isPriorityValid = checkPriority(priorityC);

    let isFormValid =
        isNomeValid &&
        isPriorityValid;

    return isFormValid;
}

const isRequired = value => value === '' ? false : true;

const isBetween = (length, min, max) => length < min || length > max ? false : true;

const nomeValid = (nome) => {
    const re = /[a-zA-Z\s\']/;
    return re.test(nome);
}

const priorityValid = (priority) => {
    const re = /^\d+$/;
    return re.test(priority);
}

const emailValid = (email) => {
    const re = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    return re.test(email);
};

const telefonoValid = (telefono) => {
    const re = /^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[\s0-9]*$/;
    return re.test(telefono);
}

const isPasswordSecure = (password) => {
    const re = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
    return re.test(password);
};

const showError = (input, message) => {
    // get the form-field element
    const formField = input.parentElement;
    // add the error class
    formField.classList.remove('success');
    formField.classList.add('error');

    // show the error message
    const error = formField.querySelector('small');
    error.textContent = message;
};

const showSuccess = (input) => {
    // get the form-field element
    const formField = input.parentElement;

    // remove the error class
    formField.classList.remove('error');
    formField.classList.add('success');

    // hide the error message
    const error = formField.querySelector('small');
    error.textContent = '';
}

const checkNome = (input) => {

    let valid = false;
    const min = 3,
        max = 255;
    const nome = input.value.trim();

    if (!isRequired(nome)) {
        showError(input, 'Il campo non può essere vuoto.');
    } else if (!isBetween(nome.length, min, max)) {
        showError(input, `Deve contenere tra ${min} e ${max} caratteri.`)
    } else if (!nomeValid(nome)) {
        showError(input, 'Nome non valido.');
    } else {
        showSuccess(input);
        valid = true;
    }
    return valid;
}

const checkTelefono = () => {

        let valid = false;
        const telefono = telefonoR.value.trim();

        if (!isRequired(telefono)) {
            showError(telefonoR, 'Il campo non può essere vuoto.');
        } else if  (!telefonoValid(telefono)) {
            showError(telefonoR, 'Numero non valido.');
        } else {
            showSuccess(telefonoR);
            valid = true;
        }
        return valid;
}
const checkCognome = () => {

    let valid = false;
    const min = 3,
        max = 255;
    const cognome = cognomeR.value.trim();

    if (!isRequired(cognome)) {
        showError(cognomeR, 'Il campo non può essere vuoto.');
    } else if (!isBetween(cognome.length, min, max)) {
        showError(cognomeR, `Deve contenere tra ${min} e ${max} caratteri.`)
    } else if (!nomeValid(cognome)) {
        showError(cognomeR, 'Cognome non valido.');
    } else {
        showSuccess(cognomeR);
        valid = true;
    }
    return valid;
}

const checkEmail = (input) => {
    let valid = false;
    const email = input.value.trim();
    if (!isRequired(email)) {
        showError(input, 'Il campo non può essere vuoto.');
    } else if (!emailValid(email)) {
        showError(input, 'Email non valida.')
    } else {
        showSuccess(input);
        valid = true;
    }
    return valid;
}

const checkPassword = (input) => {

    let valid = false;

    const password = input.value.trim();

    if (!isRequired(password)) {
        showError(input, 'Il campo non può essere vuoto');
    } else if (!isPasswordSecure(password)) {
        showError(input, 'Deve avere almeno 8 caratteri tra cui almeno 1 in minuscolo, 1 in maiuscolo, 1 numero, e 1 carattere speciale (!@#$%^&*)');
    } else {
        showSuccess(input);
        valid = true;
    }

    return valid;
};

const checkConfirmPassword = () => {
    let valid = false;

    const passwordC = passwordConferma.value.trim();
    const password = passwordR.value.trim();

    if (!isRequired(passwordC)) {
        showError(passwordConferma, 'Reinserisci la password');
    } else if (password !== passwordC) {
        showError(passwordConferma, 'La password è diversa dalla precedente');
    } else {
        showSuccess(passwordConferma);
        valid = true;
    }

    return valid;
};

const checkPriority = (input) => {

    let valid = false;
    const min = 1,
        max = 10;
    const priority = input.value.trim();

    if (!isRequired(priority)) {
        showError(input, 'Il campo non può essere vuoto.');
    } else if (!isBetween(priority.length, min, max)) {
        showError(input, `Deve contenere tra ${min} e ${max} caratteri.`)
    } else if (!nomeValid(priority)) {
        showError(input, 'Priorità non valida.');
    } else {
        showSuccess(input);
        valid = true;
    }
    return valid;
}
