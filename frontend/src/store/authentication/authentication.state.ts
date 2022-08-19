import { atom } from "recoil";
import { v4 as uuidv4 } from "uuid";
import { ATOM_AUTHENTICATION } from "../../utils/constants/state.constants";

export interface IAuthenticationAtom {
	token: string;
	id: number;
	email: string;
	nickname: string;
}

const authenticationAtom = atom({
	key: `${ATOM_AUTHENTICATION}${uuidv4()}`,
	default: { token: "", id: -1, email: "", nickname: "" },
});

export { authenticationAtom };
