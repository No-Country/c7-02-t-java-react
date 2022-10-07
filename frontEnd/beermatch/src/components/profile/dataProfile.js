import React from "react";

const style = {
  label: "block text-sm font-semibold text-PurpleNavy p-1",
  value: "text-lg p-2 font-light text-PurpleNavy",
  valueEdit:"text-lg p-2 font-light text-PurpleNavy",
};

function DataProfile() {
  return (
    <>
      <div className="flex w-auto justify-center p-2 mt-3">
        <form action="">
          <div className="block">
            <div>
              <label htmlFor="name" className={style.label}>
                Nombre
              </label>
              <input type="text" id="name" value="John" className={style.value} />
            </div>
            <div>
              <label htmlFor="lastName" className={style.label}>Apellido</label>
              <input type="text" id="lastName" value="Doe" className={style.value} />
            </div>
            <div>
              <label htmlFor="email" className={style.label}>Email</label>
              <input type="email" id="email" value="johndoe@beermatch.com" className={style.value} />
            </div>
            <div>
              <label htmlFor="genre" className={style.label}>Genero</label>
              <input type="text" id="genre" value="Indefinido" className={style.value} />
            </div>
            <div>
              <label htmlFor="birth" className={style.label}>Fecha Nacimiento</label>
              <input type="text" id="birth" value={new Date()} className={style.value} />
            </div>
          </div>
          <div>
          <div>
              <label htmlFor="pass" className={style.label}>Contrasena</label>
              <input type="password" id="pass" value={Math.random(123413245)} className={style.value} />
            </div>
          </div>
        </form>
        <div>
          <button type="reset" className="bg-green-500 rounded-lg p-1 text-sm text-white font-bold hover:bg-white hover:outline-green-500 hover:outline hover:text-green-500">Edit Profile</button>
        </div>
      </div>
    </>
  );
}

export default DataProfile;
