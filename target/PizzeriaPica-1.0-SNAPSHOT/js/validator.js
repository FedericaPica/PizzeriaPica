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

const nomeCU = document.querySelector('#nomeIdUpdate');
const priorityCU = document.querySelector('#priorityIdUpdate');
//Orario
const orario =document.querySelector('#orarioId');
//Festivo
const festivo = document.querySelector('#festivoId');
//Prodotto
const nomeProdottoI = document.querySelector("#nomeIdInsert");
const prezzoProdottoI = document.querySelector("#prezzoIdInsert");
const descrizioneProdottoI = document.querySelector('#descrizioneIdInsert');
const scontoProdottoI = document.querySelector('#scontoIdInsert');
//Ordine
const data = document.querySelector('#dataRitiro');
const ora = document.querySelector('#oraRitiro');

const nomeProdottoU = document.querySelector("#nomeIdUpdate");
const prezzoProdottoU = document.querySelector("#prezzoIdUpdate");
const descrizioneProdottoU = document.querySelector('#descrizioneIdUpdate');
const scontoProdottoU = document.querySelector('#scontoIdUpdate');

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


function validateUpdateCategoria(){
    let isNomeValid = checkNome(nomeCU),
        isPriorityValid = checkPriority(priorityCU);

    let isFormValid =
        isNomeValid &&
        isPriorityValid;

    return isFormValid;
}

function validateOrario(){
    let isOrarioValid = checkOrario(orario);

    let isFormValid =
        isOrarioValid;

    return isFormValid;
}

function validateFestivo(){
    let isFestivoValid = checkFestivo(festivo);

    let isFormValid =
        isFestivoValid;
    return isFormValid;
}

function validateInsertProdotto(){
    let isNomeValid = checkNome(nomeProdottoI)
        isPrezzoValid = checkPrezzo(prezzoProdottoI)
        isDescrizioneValid = checkDescrizione(descrizioneProdottoI)
        isScontoValid = checkSconto(scontoProdottoI);

    let isFormValid =
        isNomeValid &&
        isPrezzoValid &&
        isDescrizioneValid &&
        isScontoValid;

    return isFormValid;
}

function validateUpdateProdotto(){
    let isNomeValid = checkNome(nomeProdottoU)
    isPrezzoValid = checkPrezzo(prezzoProdottoU)
    isDescrizioneValid = checkDescrizione(descrizioneProdottoU)
    isScontoValid = checkSconto(scontoProdottoU);

    let isFormValid =
        isNomeValid &&
        isPrezzoValid &&
        isDescrizioneValid &&
        isScontoValid;

    return isFormValid;
}

const isRequired = value => value === '' ? false : true;

const isBetween = (length, min, max) => length < min || length > max ? false : true;

const nomeValid = (nome) => {
    const re = /^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/u;
    return re.test(nome);
}
const prezzoValid = (prezzo) => {
    const re = /^[0-9]+(\.)[0-9]+$/;
    return re.test(prezzo);
}

const descrizioneValid = (descrizione) => {
    const re =  /^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/u;
    return re.test(descrizione);
}

const scontoValid = (sconto) => {
    const re = /^[0-9][0-9]?$|^100$/;
    return re.test(sconto);
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
    const re = /^\d{3,4}\-\d{5,9}$/;
    return re.test(telefono);
}

const isPasswordSecure = (password) => {
    const re = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
    return re.test(password);
};

const orarioValid = (orario) => {
    const re = /^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$/;
    return re.test(orario);
}

const festivoValid = (festivo) => {
    const re = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;
    return re.test(festivo);
}

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

const checkDescrizione = (input) => {

    let valid = false;
    const min = 3,
        max = 500;
    const descrizione = input.value.trim();

    if (!isRequired(descrizione)) {
        showError(input, 'Il campo non può essere vuoto.');
    } else if (!isBetween(descrizione.length, min, max)) {
        showError(input, `Deve contenere tra ${min} e ${max} caratteri.`)
    } else if (!descrizioneValid(descrizione)) {
        showError(input, 'Descrizione non valida.');
    } else {
        showSuccess(input);
        valid = true;
    }
    return valid;
}

const checkOrario = (input) => {

    let valid = false;
    const orario = input.value.trim();

    if (!isRequired(orario)) {
        showError(input, 'Il campo non può essere vuoto.');
     } else if (!orarioValid(orario)) {
         showError(input, 'Orario non valido.');
    } else {
        showSuccess(input);
        valid = true;
    }
    return valid;
}

const checkSconto = (input) => {

    let valid = false;
    const sconto = input.value.trim();

    if (!isRequired(sconto)) {
        showError(input, 'Il campo non può essere vuoto.');
    } else if (!scontoValid(sconto)) {
        showError(input, 'Sconto non valido. (Il numero deve essere compreso tra 0 e 100)');
    } else {
        showSuccess(input);
        valid = true;
    }
    return valid;
}

const checkFestivo = (input) => {
    let valid = false;
    const festivo = input.value.trim();

    if (!isRequired(festivo)) {
        showError(input, 'Il campo non può essere vuoto.');
    } else if (!festivoValid(festivo)) {
        showError(input, 'Data non valida.');
    } else {
        showSuccess(input);
        valid = true;
    }
    return valid;
}

const checkPrezzo = (input) => {
    let valid = false;
    const prezzo = input.value.trim();

    if (!isRequired(prezzo)) {
        showError(input, 'Il campo non può essere vuoto.');
    } else if (!prezzoValid(prezzo)) {
        showError(input, 'Prezzo non valido.');
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
    } else if (!priorityValid(priority)) {
        showError(input, 'Priorità non valida.');
    } else {
        showSuccess(input);
        valid = true;
    }
    return valid;
}
