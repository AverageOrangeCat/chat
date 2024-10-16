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
import {Text} from "lucide-react";
import React from "react";
import {Link} from "@remix-run/react";

export default function RegisterForm() {
    return (
        <div className={"h-full w-full flex items-center justify-center"}>
            <Card className="w-full max-w-sm">
                <CardHeader>
                    <CardTitle className="text-2xl">Register</CardTitle>
                    <CardDescription>
                        Enter a username and password below to create your account.
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
                    <Button className="w-full">Register</Button>
                    <CardDescription className={"mt-3"}>
                        Have an account?{" "}
                        <Link to="/login" className={"text-primary"}>Login</Link>
                    </CardDescription>
                </CardFooter>
            </Card>
        </div>
    )
}
