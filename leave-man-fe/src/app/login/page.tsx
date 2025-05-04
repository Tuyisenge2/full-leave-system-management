"use client";

import { useEffect, useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import { useAppDispatch, useAppSelector } from "@/store/hooks";
import {
  loginStart,
  loginSuccess,
  loginFailure,
} from "@/store/slices/authSlice";
import {
  googleSignInStart,
  googleSignInSuccess,
} from "@/store/slices/googleSlice";
import Image from "next/image";
import basketballPlayer from "../../../public/bg-home.png";
import { loginSchema, type LoginFormData } from "@/validations/auth";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { authService } from "@/services/authService";
import { JwtService } from "@/services/jwtService";

export default function Login() {
  const router = useRouter();
  const dispatch = useAppDispatch();
  const { loading: authLoading, error: authError } = useAppSelector(
    (state) => state.auth
  );
  const {
    isAuthenticated,
    loading: googleLoading,
    error: googleError,
  } = useAppSelector((state) => state.google);

  const searchParams = useSearchParams();
  const token = searchParams.get("token");

  useEffect(() => {
    if (token) {
      if (JwtService.isTokenValid(token)) {
        const userData = JwtService.getTokenData(token);
        if (userData) {
          try {
            dispatch(googleSignInSuccess(userData));
            router.push("/dashboard");
          } catch (error) {}
        } else {
          router.push("/login");
        }
      } else {
        router.push("/login");
      }
    }
  }, [dispatch, router, token]);

  const handleGoogleSignIn = async () => {
    try{
    dispatch(googleSignInStart());
    authService.googleSignIn();
     
} catch (error) {
  
}
  };

  return (
    <div className='flex flex-col md:flex-row h-screen'>
      <div className='flex-1 flex flex-col justify-center items-center bg-white p-8 w-full md:w-1/2'>
        <div className='w-full max-w-md'>
          <h1 className='text-3xl font-bold mb-2'>WELCOME BACK</h1>
          <p className='text-gray-600 mb-6'>
            Welcome back! Please enter your details.
          </p>

          <form className='space-y-4'>
            <div>
              <input
                type='email'
                placeholder='Enter your email'
                className={`border rounded-md p-2 w-full border-gray-300`}
              />
            </div>

            <div>
              <input
                type='password'
                placeholder='**********'
                className={`border rounded-md p-2 w-full border-gray-300 `}
              />
            </div>

            <div className='flex items-center justify-between w-full'>
              <label className='flex items-center'>
                <input type='checkbox' className='mr-2' />
                Remember me
              </label>
              <a href='#' className='text-blue-600 hover:underline'>
                Forgot password?
              </a>
            </div>

            {(authError || googleError) && (
              <div className='text-red-500 text-sm'>
                {authError || googleError}
              </div>
            )}

            <button
              type='submit'
              disabled={authLoading}
              className='bg-red-600 text-white px-6 py-2 rounded-lg w-full hover:bg-red-700 transition duration-300 disabled:opacity-50 disabled:cursor-not-allowed'
            >
              {authLoading ? "Signing in..." : "Sign in"}
            </button>

            <button
              type='button'
              onClick={() => handleGoogleSignIn()}
              disabled={googleLoading}
              className='bg-gray-200 text-gray-800 px-6 py-2 rounded-lg w-full hover:bg-gray-300 transition duration-300 disabled:opacity-50 disabled:cursor-not-allowed'
            >
              {googleLoading
                ? "Signing in with Google..."
                : "Sign in with Google"}
            </button>

            <p className='text-gray-600'>
              Don't have an account?{" "}
              <a href='#' className='text-blue-600 hover:underline'>
                Sign up with google
              </a>
            </p>
          </form>
        </div>
      </div>

      <div className='hidden flex-1 md:flex md:w-full w-full justify-center items-center bg-gray-200'>
        <Image
          src={basketballPlayer}
          alt='Basketball Player Illustration'
          className='object-cover w-[100%] h-[100%]'
        />
      </div>
    </div>
  );
}
