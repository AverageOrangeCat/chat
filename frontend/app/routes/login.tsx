import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from "~/components/ui/card"
import {Input} from "~/components/ui/input"
import {Label} from "~/components/ui/label"
import {Button} from "~/components/ui/button";
import {Link} from "@remix-run/react";
import React from "react";

export default function LoginForm() {
    return (
        <div className={"h-full w-full flex items-center justify-center"}>
            <Card className="w-full max-w-sm">
                <CardHeader>
                    <CardTitle className="text-2xl">Login</CardTitle>
                    <CardDescription>
                        Enter your username below to login to your account.
                    </CardDescription>
                </CardHeader>
                <CardContent className="grid gap-4">
                    <div className="grid gap-2">
                        <Label htmlFor="username">Username</Label>
                        <Input id="username" type="username" placeholder="" required/>
                    </div>
                    <div className="grid gap-2">
                        <Label htmlFor="password">Password</Label>
                        <Input id="password" type="password" required/>
                    </div>
                </CardContent>
                <CardFooter className={"flex flex-col"}>
                    <Button className="w-full">Sign in</Button>
                    <CardDescription className={"mt-3"}>
                        Don't have an account?{" "}
                        <Link to="/register" className={"text-primary"}>Register</Link>
                    </CardDescription>
                </CardFooter>
            </Card>
        </div>
    )
}
