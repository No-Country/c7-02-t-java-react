import axios from "axios";
import React from "react";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRouter } from "next/router";
import Cookies from "js-cookie";

export default function NewPost({ allBusiness }) {
  const [attentionRate, setAttentionRate] = React.useState("");
  const [placeRate, setPlaceRate] = React.useState("");
  const [priceRate, setPriceRate] = React.useState("");
  const [qualityRate, setQualityRate] = React.useState("");
  const [text, setText] = React.useState("");
  const [businessID, setBusinessID] = React.useState("");

  const router = useRouter();

  console.log(businessID);
  const [userID, setUserID] = React.useState("");
  const [token, setToken] = React.useState("");

  React.useEffect(() => {
    const userID = Cookies.get("userID");
    setUserID(userID);
    const token = Cookies.get("token");
    setToken(token);
  }, []);

  console.log(text.length);

  const handleNewBusiness = async (e) => {
    const baseURL = `http://localhost:8080/review/${businessID}`;

    e.preventDefault();
    await axios
      .post(
        baseURL,
        {
          attentionRate: attentionRate,
          placeRate: placeRate,
          priceRate: priceRate,
          qualityRate: qualityRate,
          text: text,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((response) => {
        console.log(response.data);
        toast.success("Revisión creada !", {
          position: toast.POSITION.TOP_CENTER,
        });
        e.target.reset();
        router.push("/dashboard/main");
      })
      .catch((error) => {
        toast.error("Error de creación, pruebe nuevamente", {
          position: toast.POSITION.TOP_CENTER,
        });
        console.log(error.message);
      });
  };

  console.log({
    attentionRate: attentionRate,
    placeRate: placeRate,
    priceRate: priceRate,
    qualityRate: qualityRate,
    text: text,
  });

  const cleanState = () => {
    setAttentionRate("");
    setBusinessID("");
    setPlaceRate("");
    setPriceRate("");
    setQualityRate("");
    setText("");
  };

  const inputOnInvalid = (e) => {
    e.target.setCustomValidity("Campo obligatorio");
  };

  const inputOnInput = (e) => {
    e.target.setCustomValidity("");
  };

  const style = {
    input:
      " flex pl-2 w-full font-light text-gray-800 bg-gray-50 outline-none border-none",
    inputContainer:
      "flex items-center border-2 bg-gray-50 border-gray-50 my-6 mx-2 p-0.5 px-3 rounded-xl outline outline-1 hover:outline-violet-500 hover:outline hover:outline-2 ",
  };

  console.log(attentionRate);

  return (
    <>
      <div className="m-4 lg:m-2 flex lg:justify-end justify-center ">
        <label
          htmlFor="my-modal"
          className="cursor-pointer rounded-xl uppercase modal-button bg-PurpleNavy text-white outline outline-2 hover:outline-2 py-2.5 px-3 hover:text-PurpleNavy hover:outline-MiddleYellow hover:bg-MiddleYellow"
        >
          Nuevo review
        </label>
        <input type="checkbox" id="my-modal" className="modal-toggle" />
        <div className="modal">
          <ToastContainer autoClose={2000} />
          <div className="modal-box bg-gray-50 w-full">
            <form onSubmit={handleNewBusiness}>
              <div>
                <h3 className="font-bold text-lg text-PurpleNavy my-2 flex justify-center">
                  Crea tu review
                </h3>
                <select
                  className="select select-primary w-fit max-w-xs bg-gray-200 flex mx-auto text-PurpleNavy"
                  onChange={(e) => setBusinessID(e.target.value)}
                >
                  <option disabled selected>
                    Selecciona un negocio
                  </option>
                  {allBusiness.map((item) => (
                    <option value={item.id}>{item.name}</option>
                  ))}
                </select>
                <div className="flex space-y-2 p-2 my-2 w-full text-PurpleNavy justify-between  ">
                  <label
                    htmlFor="attentionRate"
                    className=" font-semibold text-sm mb-1 mt-2"
                  >
                    Nota Atención: 
                  </label>
                  <div className="rating mr-4 rating-md space-x-8 justify-center">
                    <input
                      type="radio"
                      name="rating-2"
                      value={1}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setAttentionRate(e.target.value)}
                    />
                    <input
                      type="radio"
                      name="rating-2"
                      value={2}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setAttentionRate(e.target.value)}
                    />
                    <input
                      type="radio"
                      name="rating-2"
                      value={3}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setAttentionRate(e.target.value)}
                    />
                    <input
                      type="radio"
                      name="rating-2"
                      value={4}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setAttentionRate(e.target.value)}
                    />
                    <input
                      type="radio"
                      name="rating-2"
                      value={5}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setAttentionRate(e.target.value)}
                    />
                  </div>
                </div>
                <div className="flex space-y-2 p-2 my-2 w-full text-PurpleNavy justify-between ">
                  <label
                    htmlFor="attentionRate"
                    className=" font-semibold text-sm mb-1 mt-2"
                  >
                    Nota Lugar: 
                  </label>
                  <div className="rating mr-4 rating-md space-x-8 justify-center">
                    <input
                      type="radio"
                      name="rating-3"
                      value={1}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setPlaceRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-3"
                      value={2}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setPlaceRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-3"
                      value={3}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setPlaceRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-3"
                      value={4}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setPlaceRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-3"
                      value={5}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setPlaceRate(e.target.value)}
                      />
                  </div>
                </div>
                <div className="flex space-y-2 p-2 my-2 w-full text-PurpleNavy justify-between ">
                  <label
                    htmlFor="qualityRate"
                    className=" font-semibold text-sm mb-1 mt-2"
                  >
                    Nota Precio: 
                  </label>
                  <div className="rating mr-4 rating-md space-x-8 justify-center">
                    <input
                      type="radio"
                      name="rating-4"
                      value={1}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setPriceRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-4"
                      value={2}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setPriceRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-4"
                      value={3}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setPriceRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-4"
                      value={4}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setPriceRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-4"
                      value={5}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setPriceRate(e.target.value)}
                      />
                  </div>
                </div>
                <div className="flex space-y-2 p-2 my-2 w-full text-PurpleNavy justify-between ">
                  <label
                    htmlFor="attentionRate"
                    className=" font-semibold text-sm mb-1 mt-2"
                  >
                    Nota Calidad: 
                  </label>
                  <div className="rating mr-4 rating-md space-x-8 justify-center">
                    <input
                      type="radio"
                      name="rating-5"
                      value={1}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setQualityRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-5"
                      value={2}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setQualityRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-5"
                      value={3}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setQualityRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-5"
                      value={4}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setQualityRate(e.target.value)}
                      />
                    <input
                      type="radio"
                      name="rating-5"
                      value={5}
                      className="mask mask-star-2 bg-yellow-400"
                      onChange={(e) => setQualityRate(e.target.value)}
                      />
                  </div>
                </div>

   
                <div className={style.inputContainer}>
                  <textarea
                    id="text"
                    className={style.input}
                    rows="5"
                    type="text"
                    name="text"
                    maxLength="256"
                    placeholder="Comentarios de review"
                    onChange={(e) => setText(e.target.value)}
                    onInvalid={inputOnInvalid}
                    onInput={inputOnInput}
                    required
                  ></textarea>
                </div>
                <div className="modal-action flex justify-between p-2">
                  <label
                    htmlFor="my-modal"
                    className="btn btn-outline btn-error"
                    onClick={() => cleanState()}
                  >
                    CERRAR
                  </label>
                  <label htmlFor="my-modal">
                    <button
                      type="submit"
                      htmlFor="my-modal"
                      className="btn btn-outline btn-info"
                    >
                      Guardar
                    </button>
                  </label>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
}
